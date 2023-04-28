package cn.chenzw.toolkit.core.util;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzw
 */
public class BeanKit {

    /**
     * 复制属性
     *
     * @param source 源对象
     * @param target 复制对象
     */
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }


    /**
     * Bean => Map
     *
     * @param o
     * @return
     */
    public static Map<String, Object> toMap(Object o) {
        Map<String, Object> map = new HashMap<>(16);
        if (o != null) {
            BeanMap beanMap = BeanMap.create(o);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

}
