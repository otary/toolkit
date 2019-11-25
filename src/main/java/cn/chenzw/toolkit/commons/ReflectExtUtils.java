package cn.chenzw.toolkit.commons;

import cn.chenzw.toolkit.commons.exception.FieldNotExistException;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

/**
 * 反射类扩展
 *
 * @author chenzw
 */
public final class ReflectExtUtils {

    private static final Map<Class<?>, Field[]> FIELDS_CACHE = new WeakHashMap();

    private static final Map<Class<?>, Method[]> METHODS_CACHE = new WeakHashMap<>();


    /**
     * 设置字段值（字段不存在时抛出异常）
     *
     * @param o
     * @param fieldName
     * @param value
     * @throws IllegalAccessException
     * @throws FieldNotExistException
     */
    public static void setFieldValue(Object o, String fieldName, Object value) throws IllegalAccessException, FieldNotExistException {
        Objects.requireNonNull(o, "object is null");
        Objects.requireNonNull(fieldName, "fieldName is null.");

        Class<?> aClass = o.getClass();
        Field field = getField(aClass, fieldName);

        if (field == null) {
            throw new FieldNotExistException(fieldName, o);
        }
        setFieldValue(o, field, value);
    }

    /**
     * 设置字段值（字段不存在时不抛出异常）
     *
     * @param o
     * @param fieldName
     * @param value
     * @throws IllegalAccessException
     */
    public static void setFieldValueQuietly(Object o, String fieldName, Object value) throws IllegalAccessException {
        Objects.requireNonNull(o, "object is null");
        Objects.requireNonNull(fieldName, "fieldName is null.");

        Class<?> aClass = o.getClass();
        Field field = getField(aClass, fieldName);

        if (field == null) {
            return;
        }
        setFieldValue(o, field, value);
    }

    public static void setFieldValue(Object o, Field field, Object value) throws IllegalAccessException {
        setAccessible(field);

        if (value != null) {
            Class<?> fieldType = field.getType();
            if (!fieldType.isAssignableFrom(value.getClass())) {
                final Object targetValue = ConvertExtUtils.convert(fieldType, value);
                if (targetValue != null) {
                    value = targetValue;
                }
            }
        }
        field.set(o, value);
    }

    public static Object getFieldValue(Object o, Field field) throws IllegalAccessException {
        Objects.requireNonNull(o, "object must not be null");
        Objects.requireNonNull(field, "field must not be null.");

        setAccessible(field);
        return field.get(o);
    }

    /**
     * 获取字段值（字段不存在时抛出异常）
     *
     * @param o
     * @param fieldName
     * @return
     * @throws IllegalAccessException
     */
    public static Object getFieldValue(Object o, String fieldName) throws IllegalAccessException, FieldNotExistException {
        Objects.requireNonNull(o, "object must not be null");
        Objects.requireNonNull(fieldName, "fieldName must not be null.");

        Field field = getField(o.getClass(), fieldName);
        if (field == null) {
            throw new FieldNotExistException(fieldName, o);
        }
        return getFieldValue(o, field);
    }

    /**
     * 获取字段值（字段不存在时返回null）
     *
     * @param o
     * @param fieldName
     * @return
     * @throws IllegalAccessException
     */
    public static Object getFieldValueQuietly(Object o, String fieldName) throws IllegalAccessException {
        Objects.requireNonNull(o, "object must not be null");
        Objects.requireNonNull(fieldName, "fieldName must not be null.");

        Field field = getField(o.getClass(), fieldName);
        if (field == null) {
            return null;
        }
        return getFieldValue(o, field);
    }


    /**
     * 获取类的所有字段（包括父类的）
     *
     * @param aClass
     * @return
     */
    public static Field[] getFields(Class<?> aClass) {

        if (FIELDS_CACHE.containsKey(aClass)) {
            return FIELDS_CACHE.get(aClass);
        }

        Field[] declaredFields = aClass.getDeclaredFields();

        Class<?> superClass = aClass.getSuperclass();
        while (superClass != null) {
            Field[] superClassFileds = superClass.getDeclaredFields();
            declaredFields = ArrayUtils.addAll(declaredFields, superClassFileds);
            superClass = superClass.getSuperclass();
        }

        FIELDS_CACHE.put(aClass, declaredFields);
        return declaredFields;
    }

    /**
     * 获取类中指定的字段
     *
     * @param aClass
     * @param fieldName
     * @return
     */
    public static Field getField(Class<?> aClass, String fieldName) {
        Field[] fields = getFields(aClass);
        if (ArrayUtils.isNotEmpty(fields)) {
            for (Field field : fields) {
                if (fieldName.equals(field.getName())) {
                    return field;
                }
            }
        }
        return null;
    }


    /**
     * 设置方法可访问
     *
     * @param accessibleObject 可设置访问权限的对象，如Class、Method、Field等
     * @param <T>
     * @return
     */
    public static <T extends AccessibleObject> T setAccessible(T accessibleObject) {
        if (accessibleObject != null && !accessibleObject.isAccessible()) {
            accessibleObject.setAccessible(true);
        }
        return accessibleObject;
    }

    /**
     * 获取类中的所有方法（包括父类的）
     *
     * @param aClass
     * @return
     */
    public static Method[] getMethods(Class<?> aClass) {
        if (METHODS_CACHE.containsKey(aClass)) {
            return METHODS_CACHE.get(aClass);
        }

        Method[] declaredMethods = aClass.getDeclaredMethods();
        Class<?> superClass = aClass.getSuperclass();
        while (superClass != null) {
            Method[] superClassMethods = superClass.getDeclaredMethods();
            declaredMethods = ArrayUtils.addAll(declaredMethods, superClassMethods);
            superClass = superClass.getSuperclass();
        }
        METHODS_CACHE.put(aClass, declaredMethods);
        return declaredMethods;
    }

}
