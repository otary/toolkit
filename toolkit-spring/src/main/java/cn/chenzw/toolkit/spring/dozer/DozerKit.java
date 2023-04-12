package cn.chenzw.toolkit.spring.dozer;

import cn.chenzw.toolkit.core.lang.GenericKit;
import cn.chenzw.toolkit.spring.dozer.core.DozerFieldMapping;
import com.github.dozermapper.core.CustomConverter;
import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.builder.BeanMappingsBuilder;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.FieldsMappingOptions;
import com.github.dozermapper.core.loader.api.TypeMappingBuilder;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * Dozer工具类
 *
 * @author chenzw
 */
public final class DozerKit {

    private DozerKit() {
    }

    /**
     * List对象互转
     *
     * @param mapper      {@link Mapper}
     * @param sourceList  源List对象
     * @param targetClass 目标对象类型
     * @return 目标List对象
     */
    public static <T, S> List<T> mapList(final Mapper mapper, List<S> sourceList, Class<T> targetClass) {
        if (sourceList == null || sourceList.isEmpty()) {
            return new ArrayList<>();
        }
        List<T> targetList = new ArrayList<>();
        for (S s : sourceList) {
            targetList.add(mapper.map(s, targetClass));
        }
        return targetList;
    }

    /**
     * List对象互转
     *
     * @param sourceList
     * @param targetClass
     * @param <T>
     * @param <S>
     * @return 目标List对象
     */
    public static <T, S> List<T> mapList(List<S> sourceList, Class<T> targetClass) {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        return mapList(mapper, sourceList, targetClass);
    }

    /**
     * 支持字段自定义转换的映射
     *
     * @param sourceList
     * @param destClass
     * @param dozerFieldMappings
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> List<T> mapList(
            List<S> sourceList,
            Class<T> destClass,
            List<DozerFieldMapping> dozerFieldMappings
    ) {
        if (sourceList == null || sourceList.isEmpty()) {
            return new ArrayList<>();
        }

        Map<String, CustomConverter> customConverterMap = new HashMap<>();
        for (DozerFieldMapping dozerFieldMapping : dozerFieldMappings) {
            customConverterMap.put(ObjectUtils.identityToString(dozerFieldMapping), dozerFieldMapping.getConverter());
        }
        // ((DozerBeanMapper) mapper).setCustomConvertersWithId(customConverterMap);

        BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                TypeMappingBuilder mapping = mapping(GenericKit.getSuperClassGenericType(sourceList), destClass);
                for (DozerFieldMapping dozerFieldMapping : dozerFieldMappings) {
                    mapping.fields(dozerFieldMapping.getSrcFieldName(), dozerFieldMapping.getDestFieldName(),
                            FieldsMappingOptions.customConverterId(ObjectUtils.identityToString(dozerFieldMapping)));
                }
            }
        };
        // ((DozerBeanMapper) mapper).addMapping(builder);

        Mapper mapper = DozerBeanMapperBuilder.create()
                .withCustomConvertersWithIds(customConverterMap)
                .withMappingBuilder(builder)
                .build();

        List<T> results = mapList(mapper, sourceList, destClass);
        return results;
    }

}
