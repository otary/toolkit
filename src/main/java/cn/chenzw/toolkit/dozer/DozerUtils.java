package cn.chenzw.toolkit.dozer;

import cn.chenzw.toolkit.commons.GenericUtils;
import cn.chenzw.toolkit.dozer.core.DozerFieldMapping;
import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.dozer.loader.api.TypeMappingBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象映射工具类
 *
 * @author chenzw
 */
public abstract class DozerUtils {

    private static final Logger logger = LoggerFactory.getLogger(DozerUtils.class);


    private DozerUtils() {
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

    public static <T, S> List<T> mapList(List<S> sourceList, Class<T> targetClass) {
        Mapper mapper = new DozerBeanMapper();
        return mapList(mapper, sourceList, targetClass);
    }

    /**
     * 支持字段自定义转换的映射
     *
     * @param mapper
     * @param sourceList
     * @param destClass
     * @param dozerFieldMappings
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> List<T> mapList(final Mapper mapper, List<S> sourceList, Class<T> destClass,
            List<DozerFieldMapping> dozerFieldMappings) {
        logger.debug("Dozer [{}] start mapList, source:{}, dest: {}", dozerFieldMappings, sourceList, destClass);
        long t1 = System.currentTimeMillis();

        if (sourceList == null || sourceList.isEmpty()) {
            return new ArrayList<T>();
        }

        Map<String, CustomConverter> customConverterMap = new HashMap<>();
        for (DozerFieldMapping dozerFieldMapping : dozerFieldMappings) {
            customConverterMap.put(ObjectUtils.identityToString(dozerFieldMapping), dozerFieldMapping.getConverter());
        }
        ((DozerBeanMapper) mapper).setCustomConvertersWithId(customConverterMap);

        BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                TypeMappingBuilder mapping = mapping(GenericUtils.getSuperClassGenricType(sourceList), destClass);
                for (DozerFieldMapping dozerFieldMapping : dozerFieldMappings) {
                    mapping.fields(dozerFieldMapping.getSrcFieldName(), dozerFieldMapping.getDestFieldName(),
                            FieldsMappingOptions.customConverterId(ObjectUtils.identityToString(dozerFieldMapping)));
                }
            }
        };
        ((DozerBeanMapper) mapper).addMapping(builder);
        List<T> rets = mapList(mapper, sourceList, destClass);

        long t2 = System.currentTimeMillis();
        logger.debug("Dozer [{}] finsh mapList, cost {} ms", mapper, (t2 - t1));

        return rets;
    }

}
