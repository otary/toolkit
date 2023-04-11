package cn.chenzw.toolkit.spring.util;

import cn.chenzw.toolkit.core.util.ClassKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
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
@Slf4j
public abstract class ResourceScannerKit {

    private static final String RESOURCE_PATTERN = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "**/%s/**/*.%s";

    private ResourceScannerKit() {
    }

    /**
     * 扫描类对象
     *
     * @param scanPackage
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Set<Class<?>> scanClass(String scanPackage)
            throws IOException, ClassNotFoundException {
        return doScanClass(scanPackage, null);
    }

    /**
     * 扫描超类及其后裔类
     *
     * @param scanPackage
     * @param superClasses
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Set<Class<?>> scanClassFromSuper(String scanPackage, Class<?>[] superClasses) throws IOException, ClassNotFoundException {

        List<AssignableTypeFilter> typeFilters = new LinkedList<>();
        if (ArrayUtils.isNotEmpty(superClasses)) {
            for (Class<?> superCls : superClasses) {
                typeFilters.add(new AssignableTypeFilter(superCls));
            }
        }
        return doScanClass(scanPackage, typeFilters);
    }


    /**
     * 扫描指定注解标注的类（不包含注解类）
     *
     * @param scanPackage
     * @param annotationTypes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Set<Class<?>> scanClassFromAnnotation(String scanPackage, Class<? extends Annotation>[] annotationTypes)
            throws IOException, ClassNotFoundException {
        return scanClassFromAnnotation(scanPackage, annotationTypes, false);
    }


    /**
     * 扫描指定注解标注的类
     *
     * @param scanPackage
     * @param annotationTypes
     * @param considerMetaAnnotations 是否包含注解类
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Set<Class<?>> scanClassFromAnnotation(String scanPackage, Class<? extends Annotation>[] annotationTypes, boolean considerMetaAnnotations) throws IOException, ClassNotFoundException {
        List<AnnotationTypeFilter> typeFilters = new LinkedList<>();
        if (ArrayUtils.isNotEmpty(annotationTypes)) {
            for (Class<? extends Annotation> annotationType : annotationTypes) {
                typeFilters.add(new AnnotationTypeFilter(annotationType, considerMetaAnnotations));
            }
        }
        return doScanClass(scanPackage, typeFilters);
    }


    private static Set<Class<?>> doScanClass(String scanPackage, List<? extends TypeFilter> typeFilters)
            throws IOException, ClassNotFoundException {
        Set<Class<?>> foundClasses = new HashSet<>();
        Set<Resource> resources = scan(scanPackage, SUFFIX.CLASS);

        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
        for (Resource resource : resources) {
            MetadataReader reader = readerFactory.getMetadataReader(resource);
            Class<?> cls = ClassKit.forName(reader.getClassMetadata().getClassName());

            if (CollectionUtils.isEmpty(typeFilters)) {
                foundClasses.add(cls);
            } else {
                for (TypeFilter typeFilter : typeFilters) {
                    if (typeFilter.match(reader, readerFactory)) {
                        foundClasses.add(cls);
                    }
                }
            }
        }
        return foundClasses;
    }


    /**
     * 扫描获取指定包路径下指定后缀的文件
     *
     * @param scanPackage 扫描的包路径
     * @param suffix      {@link SUFFIX} 后缀名称
     * @return
     * @throws IOException
     */
    public static Set<Resource> scan(String scanPackage, String suffix) throws IOException {

        if (StringUtils.isBlank(scanPackage)) {
            throw new IllegalArgumentException("The scanPackage path is Null!");
        }
        suffix = StringUtils.defaultIfEmpty(suffix, SUFFIX.CLASS);

        Set<Resource> foundResources = new HashSet<>();
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

        String[] scanPackages = scanPackage.split(",|;");
        for (String scanPack : scanPackages) {
            Resource[] resources = resourcePatternResolver.getResources(
                    String.format(RESOURCE_PATTERN, ClassUtils.convertClassNameToResourcePath(scanPack), suffix));
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    foundResources.add(resource);
                } else {
                    log.warn("Resource [ {} ] unReadable!", resource.getFilename());
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

        private SUFFIX() {
        }
    }

}
