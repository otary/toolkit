package cn.chenzw.toolkit.commons;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

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
    public void testSetFieldValue() throws IllegalAccessException {
        Children children = new Children();

        ReflectExtUtils.setFieldValue(children, "childName", "张三");
        ReflectExtUtils.setFieldValue(children, "fatherName", "李四");

        Assert.assertEquals("张三", children.getChildName());
        Assert.assertEquals("李四", children.getFatherName());
    }

    @Test
    public void testGetMethods() {
        Method[] methods = ReflectExtUtils.getMethods(Children.class);
        System.out.println(Arrays.toString(methods));
    }

    @Test
    public void testGetFieldValue() throws IllegalAccessException {
        Children children = new Children();
        children.setChildName("张三");
        children.setFatherName("李四");

        Object childName = ReflectExtUtils.getFieldValue(children, "childName");
        Assert.assertEquals("张三", childName);

        Object fatherName = ReflectExtUtils.getFieldValue(children, "fatherName");
        Assert.assertEquals("李四", fatherName);
    }
}


class Father {
    private String fatherName;
    public String fatherId;
    protected String fatherAge;

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getFatherAge() {
        return fatherAge;
    }

    public void setFatherAge(String fatherAge) {
        this.fatherAge = fatherAge;
    }
}

class Children extends Father {
    private String childName;
    private String childId;
    private String childAge;

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getChildAge() {
        return childAge;
    }

    public void setChildAge(String childAge) {
        this.childAge = childAge;
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