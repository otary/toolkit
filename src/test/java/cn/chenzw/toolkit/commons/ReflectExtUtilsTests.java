package cn.chenzw.toolkit.commons;

import cn.chenzw.toolkit.commons.exception.FieldNotExistException;
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
    public void testSetFieldValue() throws IllegalAccessException, FieldNotExistException {
        Children children = new Children();

        ReflectExtUtils.setFieldValue(children, "childName", "张三");
        ReflectExtUtils.setFieldValue(children, "fatherName", "李四");
        ReflectExtUtils.setFieldValue(children, "childId", 1);

        ReflectExtUtils.setFieldValue(children, "childId2", 1); // 字段不存在

        Assert.assertEquals("张三", children.getChildName());
        Assert.assertEquals("李四", children.getFatherName());
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
}

class Children extends Father {
    private String childName;
    private Long childId;
    private String childAge;

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