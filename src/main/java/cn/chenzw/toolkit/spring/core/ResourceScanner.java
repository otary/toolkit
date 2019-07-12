package cn.chenzw.toolkit.spring.core;

import cn.chenzw.toolkit.commons.ClassExtUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * 资源扫描器
 *
 * @author chenzw
 */
public class ResourceScanner {

    private static final Logger logger = LoggerFactory.getLogger(ResourceScanner.class);
    private static final String RESOURCE_PATTERN = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "**/%s/**/*.%s";

    private String[] scanPackages;
    private String suffix;
    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    private Class<? extends Annotation>[] annotationTypes;

   /* public ResourceScanner(Class<? extends Annotation>... annotationTypes) {
        this.scanPackages = scanPackages;
        this.suffix = suffix;
        this.annotationTypes = annotationTypes;
    }*/

    public static void main(String[] args) throws IOException {
        ResourceScanner resourceScanner = new ResourceScanner();
        Set<Resource> scan = resourceScanner.scan(new String[]{"cn.chenzw"}, SUFFIX.XML);
        System.out.println(scan);
    }

    public Set<Class<?>> scanClass(String[] scanPackages, Class<? extends Annotation>... annotationTypes)
            throws IOException, ClassNotFoundException {

        List<AnnotationTypeFilter> typeFilters = new LinkedList<AnnotationTypeFilter>();
        if (ArrayUtils.isNotEmpty(annotationTypes)) {
            for (Class<? extends Annotation> annotationType : annotationTypes) {
                typeFilters.add(new AnnotationTypeFilter(annotationType, false));
            }
        }

        Set<Resource> resources = scan(scanPackages, SUFFIX.CLASS);
        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
        for (Resource resource : resources) {
            MetadataReader reader = readerFactory.getMetadataReader(resource);
            Class<?> cls = ClassExtUtils.forName(reader.getClassMetadata().getClassName());


        }

    }


    public Set<Resource> scan(String[] scanPackages, String suffix) throws IOException {

        if (ArrayUtils.isEmpty(scanPackages)) {
            throw new IllegalArgumentException("The scanPackage path is Null!");
        }
        suffix = StringUtils.defaultIfEmpty(suffix, SUFFIX.CLASS);

        Set<Resource> foundResources = new HashSet<>();
        for (String scanPackage : scanPackages) {
            Resource[] resources = this.resourcePatternResolver.getResources(
                    String.format(RESOURCE_PATTERN, ClassUtils.convertClassNameToResourcePath(scanPackage), suffix));
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    foundResources.add(resource);
                } else {
                    logger.warn("Resource [ {} ] unReadable!", resource.getFilename());
                }
            }
        }
        return foundResources;
    }


    /**
     * 后缀
     */
    public static class SUFFIX {
        public static final String ALL = "*";
        public static final String XML = "xml";
        public static final String CLASS = "class";
    }

}
