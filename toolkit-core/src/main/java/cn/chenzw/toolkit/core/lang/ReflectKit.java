package cn.chenzw.toolkit.core.lang;

import cn.chenzw.toolkit.core.exception.FieldNotExistException;
import cn.chenzw.toolkit.core.util.ClassKit;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.function.BiPredicate;

/**
 * 反射类扩展
 *
 * @author chenzw
 */
public final class ReflectKit {

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
    public static void setFieldValue(Object o, String fieldName, Object value)
            throws IllegalAccessException, FieldNotExistException {
        Objects.requireNonNull(o, "object is null");
        Objects.requireNonNull(fieldName, "fieldName is null");

        Field field = getField((o instanceof Class) ? (Class<?>) o : o.getClass(), fieldName);
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
        Objects.requireNonNull(o, "object must not be null.");
        Objects.requireNonNull(fieldName, "fieldName must not be null.");

        Field field = getField((o instanceof Class) ? (Class<?>) o : o.getClass(), fieldName);
        if (field == null) {
            return;
        }
        setFieldValue(o, field, value);
    }

    /**
     * 设置字段值
     *
     * @param o
     * @param field
     * @param value
     * @throws IllegalAccessException
     */
    public static void setFieldValue(Object o, Field field, Object value) throws IllegalAccessException {
        setAccessible(field);
        field.set(o, tryConvert(field, value));
    }

    /**
     * 设置静态常量值
     *
     * @param o
     * @param fieldName
     * @param value
     * @throws NoSuchFieldException
     * @throws FieldNotExistException
     * @throws IllegalAccessException
     */
    public static void setStaticFinalFieldValue(Object o, String fieldName, Object value)
            throws NoSuchFieldException, FieldNotExistException, IllegalAccessException {
        Objects.requireNonNull(o, "object must not be null.");
        Objects.requireNonNull(fieldName, "fieldName must not be null.");

        Field field = getField((o instanceof Class) ? (Class<?>) o : o.getClass(), fieldName);
        if (field == null) {
            throw new FieldNotExistException(fieldName, o);
        }
        setStaticFinalFieldValue(field, value);
    }


    /**
     * 设置静态常量值
     *
     * @param field
     * @param value
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void setStaticFinalFieldValue(Field field, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        setAccessible(field);
        setFinalAccessible(field);
        field.set(null, tryConvert(field, value));
    }

    public static void setFinalAccessible(Field field) throws NoSuchFieldException, IllegalAccessException {
        Field modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
    }


    private static Object tryConvert(Field field, Object value) {
        if (value != null) {
            Class<?> fieldType = field.getType();
            if (!fieldType.isAssignableFrom(value.getClass())) {
                final Object targetValue = ConvertKit.convert(fieldType, value);
                if (targetValue != null) {
                    return targetValue;
                }
            }
        }
        return value;
    }

    /**
     * 获取字段值
     *
     * @param o
     * @param field
     * @return
     * @throws IllegalAccessException
     */
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
     * 获取多层级嵌套的字段值
     *
     * @param o
     * @param nestedFieldName
     * @return
     * @throws FieldNotExistException
     * @throws IllegalAccessException
     * @since 1.0.3
     */
    public static Object getNestedFieldValue(Object o, String nestedFieldName, String separator) throws FieldNotExistException, IllegalAccessException {
        Objects.requireNonNull(nestedFieldName, "Parameter \"nestedFieldName\" must not be null!");
        Objects.requireNonNull(separator, "Parameter \"separator\" must not be null!");

        String[] fieldNames = nestedFieldName.split(separator);
        Object target = o;
        for (String fieldName : fieldNames) {
            target = getFieldValue(target, fieldName);
            if (target == null) {
                return null;
            }
        }
        return target;
    }

    /**
     * 获取多层级嵌套的字段值
     *
     * @param o
     * @param nestedFieldName
     * @return
     * @throws IllegalAccessException
     * @throws FieldNotExistException
     * @since 1.0.3
     */
    public static Object getNestedFieldValue(Object o, String nestedFieldName) throws IllegalAccessException, FieldNotExistException {
        return getNestedFieldValue(o, nestedFieldName, "#");
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
        return getFields(aClass, true);
    }

    /**
     * 获取类的所有字段
     *
     * @param aClass
     * @param includeSuperClass 是否包含父类字段
     * @return
     */
    public static Field[] getFields(Class<?> aClass, boolean includeSuperClass) {
        if (FIELDS_CACHE.containsKey(aClass)) {
            return FIELDS_CACHE.get(aClass);
        }

        Field[] declaredFields = aClass.getDeclaredFields();
        if (includeSuperClass) {
            Class<?> superClass = aClass.getSuperclass();
            while (superClass != null) {
                Field[] superClassFileds = superClass.getDeclaredFields();
                declaredFields = ArrayUtils.addAll(declaredFields, superClassFileds);
                superClass = superClass.getSuperclass();
            }
        }

        FIELDS_CACHE.put(aClass, declaredFields);
        return declaredFields;
    }

    /**
     * 将对象转换成Map
     *
     * @param o
     * @param includeSuperClass
     * @return
     * @throws IllegalAccessException
     * @since 1.0.3
     */
    public static Map<String, Object> getFieldsAsMap(Object o, boolean includeSuperClass) throws IllegalAccessException {
        return getFieldsAsMap(o, includeSuperClass, (field, _o) -> true);
    }

    /**
     * 将对象转换成Map
     *
     * @param o
     * @param includeSuperClass
     * @param filter            字段过滤
     * @return
     * @throws IllegalAccessException
     * @since 1.0.3
     */
    public static Map<String, Object> getFieldsAsMap(Object o, boolean includeSuperClass, BiPredicate<Field, Object> filter) throws IllegalAccessException {
        Map<String, Object> fieldMap = new HashMap<>();
        Field[] fields = getFields(o.getClass(), includeSuperClass);
        for (Field field : fields) {
            if (filter.test(field, o)) {
                fieldMap.put(field.getName(), getFieldValue(o, field));
            }
        }
        return fieldMap;
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

    /**
     * @param o
     * @param methodName
     * @param args
     * @return
     */
    public static Method getMethod(Object o, String methodName, Object... args) {
        Method[] methods = getMethods(o.getClass());

        Class<?>[] paramTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            paramTypes[i] = (args[i] == null ? Object.class : args[i].getClass());
        }
        if (methods != null && methods.length > 0) {
            for (Method method : methods) {
                if (StringUtils.contains(method.getName(), methodName)) {
                    if (ClassKit.isAllAssignableFrom(method.getParameterTypes(), paramTypes)) {
                        return method;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 反射调用方法
     *
     * @param o
     * @param methodName
     * @param args
     * @param <T>
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> T invoke(Object o, String methodName, Object... args) throws InvocationTargetException, IllegalAccessException {
        Objects.requireNonNull(o, "object must not be null!");

        if (StringUtils.isEmpty(methodName)) {
            throw new IllegalArgumentException("methodName must not be null!");
        }

        Method method = getMethod(o, methodName, args);
        Objects.requireNonNull(method, "No such method: [ " + methodName + " ]!");

        setAccessible(method);

        if (ClassKit.isStatic(method)) {
            return (T) method.invoke(null, args);
        } else {
            return (T) method.invoke(o, args);
        }
    }

    /**
     * 是否包含某个字段
     *
     * @param aClas
     * @param fieldName
     * @return
     * @since 1.0.3
     */
    public static boolean containsField(Class<?> aClas, String fieldName) {
        Field[] fields = getFields(aClas);
        for (Field field : fields) {
            if (fieldName.equals(field.getName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 获取方法参数名
     *
     * @param method
     * @return
     * @since 1.0.3
     */
    public static String[] getMethodParameterNames(Method method) {
        org.springframework.core.ParameterNameDiscoverer parameterNameDiscoverer = new org.springframework.core.DefaultParameterNameDiscoverer();
        return parameterNameDiscoverer.getParameterNames(method);
    }

    /**
     * 获取构造函数参数名
     *
     * @param constructor
     * @return
     * @since 1.0.3
     */
    public static String[] getConstructorParameterNames(Constructor constructor) {
        org.springframework.core.ParameterNameDiscoverer parameterNameDiscoverer = new org.springframework.core.DefaultParameterNameDiscoverer();
        return parameterNameDiscoverer.getParameterNames(constructor);
    }

}
