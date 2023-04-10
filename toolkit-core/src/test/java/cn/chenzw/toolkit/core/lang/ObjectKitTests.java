package cn.chenzw.toolkit.core.lang;

import cn.chenzw.toolkit.core.domain.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ObjectKitTests {

    @Test
    public void testIdentityHashCode() {
        String a1 = "hello";
        String a2 = "hello";
        // 字符串常量池
        Assert.assertEquals(ObjectKit.identityHashCode(a1), ObjectKit.identityHashCode(a2));

        // 使用new分配新的内存空间
        String a3 = new String("hello");
        Assert.assertNotEquals(ObjectKit.identityHashCode(a1), ObjectKit.identityHashCode(a3));
    }

    @Test
    public void testClone() {
        User user = new User();
        User clonedUser = ObjectKit.clone(user);

        Assert.assertNotEquals(
                user.hashCode(),
                clonedUser.hashCode()
        );
    }
}
