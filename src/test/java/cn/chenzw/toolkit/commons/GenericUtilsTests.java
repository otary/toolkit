package cn.chenzw.toolkit.commons;

import cn.chenzw.toolkit.domain.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class GenericUtilsTests {

    @Test
    public void testGetSuperClassGenricType() throws NoSuchMethodException {
        List<User> users = new ArrayList<>();
        User user = new User();
        users.add(user);

        // List泛型类型测试
        Class aClass = getGenericClassOfListParamter(users);
        Assert.assertEquals(aClass, User.class);

    }

    public <T> Class getGenericClassOfListParamter(List<T> ts) throws NoSuchMethodException {
        return GenericUtils.getSuperClassGenricType(ts);
    }
}
