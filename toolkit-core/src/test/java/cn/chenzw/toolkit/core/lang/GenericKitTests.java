package cn.chenzw.toolkit.core.lang;

import cn.chenzw.toolkit.core.domain.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class GenericKitTests {

    @Test
    public void testGetSuperClassGenericType() throws NoSuchMethodException {
        List<User> users = new ArrayList<>();
        User user = new User();
        users.add(user);

        // List泛型类型测试
        Class aClass = getGenericClassOfListParameter(users);
        Assert.assertEquals(User.class, aClass);
    }

    public <T> Class getGenericClassOfListParameter(List<T> ts) throws NoSuchMethodException {
        return GenericKit.getSuperClassGenericType(ts);
    }
}
