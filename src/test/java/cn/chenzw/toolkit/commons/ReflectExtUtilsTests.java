package cn.chenzw.toolkit.commons;

import cn.chenzw.toolkit.commons.exception.FieldNotExistException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

@RunWith(JUnit4.class)
public class ReflectExtUtilsTests {

    @Test
    public void testGetFields() {
        Field[] fields = ReflectExtUtils.getFields(Children.class);
        System.out.println(Arrays.toString(fields));
    }

    @Test
    public void testGetField() {
        Field childNameField = ReflectExtUtils.getField(Children.class, "childName");
        Assert.assertNotNull(childNameField);

        Field childName2Field = ReflectExtUtils.getField(Children.class, "childName2");
        Assert.assertNull(childName2Field);
    }

    @Test
    public void testSetFieldValue() throws IllegalAccessException, FieldNotExistException {
        Children children = new Children();

        ReflectExtUtils.setFieldValue(children, "childName", "张三");
        ReflectExtUtils.setFieldValue(children, "fatherName", "李四");
        ReflectExtUtils.setFieldValue(children, "childId", 1);

        Assert.assertEquals("张三", children.getChildName());
        Assert.assertEquals("李四", children.getFatherName());
    }

    @Test(expected = FieldNotExistException.class)
    public void testSetFieldValue2() throws FieldNotExistException, IllegalAccessException {
        Children children = new Children();
        ReflectExtUtils.setFieldValue(children, "childId2", 1); // 字段不存在
    }

    @Test
    public void testSetStaticFinalFieldValue() throws IllegalAccessException, FieldNotExistException, NoSuchFieldException {
        Children children = new Children();
        ReflectExtUtils.setStaticFinalFieldValue(children, "SID", "2.0");

        Assert.assertEquals("2.0", Children.getSID());
    }

    @Test
    public void testGetMethods() {
        Method[] methods = ReflectExtUtils.getMethods(Children.class);
        System.out.println(Arrays.toString(methods));
    }

    @Test
    public void testGetFieldValue() throws IllegalAccessException, FieldNotExistException {
        Children children = new Children();
        children.setChildName("张三");
        children.setFatherName("李四");

        Object childName = ReflectExtUtils.getFieldValue(children, "childName");
        Assert.assertEquals("张三", childName);

        Object fatherName = ReflectExtUtils.getFieldValue(children, "fatherName");
        Assert.assertEquals("李四", fatherName);
    }

    @Test
    public void testInvoke() throws InvocationTargetException, IllegalAccessException {
        Children children = new Children();

        // 单参数
        Object result = ReflectExtUtils.invoke(children, "say", "张三");
        Assert.assertEquals("hello,张三", result);

        // 没有参数
        Object result2 = ReflectExtUtils.invoke(children, "say");
        Assert.assertEquals("hello world!", result2);

        // 调用静态方法
        Object result3 = ReflectExtUtils.invoke(children, "go");
        Assert.assertEquals("let's go!", result3);
    }

    @Test
    public void testGetFieldsAsMap() throws IllegalAccessException {
        Children children = new Children();
        children.setChildName("张三");
        children.setFatherName("李四");

        Map<String, Object> fieldMap = ReflectExtUtils.getFieldsAsMap(children, true);

        Assert.assertEquals("{fatherName=李四, fatherAge=null, childName=张三, VERSION=1.0, fatherId=null, childAge=null, childId=null, SID=1.0}", fieldMap.toString());

        // 过滤去除fatherAge字段
        Map<String, Object> fieldMap2 = ReflectExtUtils.getFieldsAsMap(children, true, (field, o) -> (!"fatherAge".equals(field.getName())));

        Assert.assertEquals("{fatherName=李四, childName=张三, VERSION=1.0, fatherId=null, childAge=null, childId=null, SID=1.0}", fieldMap2.toString());
    }


}


class Father {
    private String fatherName;
    public Long fatherId;
    protected String fatherAge;

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    public String getFatherAge() {
        return fatherAge;
    }

    public void setFatherAge(String fatherAge) {
        this.fatherAge = fatherAge;
    }

    public String say(String name) {
        return "hello," + name;
    }

    public String say() {
        return "hello world!";
    }

    public static String go() {
        return "let's go!";
    }

}

class Children extends Father {
    private String childName;
    private Long childId;
    private String childAge;

    private static final String VERSION = "1.0";

    private static String SID = "1.0";

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public String getChildAge() {
        return childAge;
    }

    public void setChildAge(String childAge) {
        this.childAge = childAge;
    }

    public static String getVERSION() {
        return VERSION;
    }

    public static String getSID() {
        return SID;
    }

    @Override
    public String toString() {
        return "Children{" +
                "fatherId='" + fatherId + '\'' +
                ", fatherAge='" + fatherAge + '\'' +
                ", childName='" + childName + '\'' +
                ", childId='" + childId + '\'' +
                ", childAge='" + childAge + '\'' +
                '}';
    }
}
