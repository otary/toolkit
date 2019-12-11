package cn.chenzw.toolkit.commons;

import cn.chenzw.toolkit.domain.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ObjectExtUtilsTests {

    @Test
    public void testIdentityHashCode() {
        User user = new User();
        int address = ObjectExtUtils.identityHashCode(user);
        Assert.assertTrue(address > 0);

        System.out.println(address);  // => 1330278544
    }

    @Test
    public void testClone(){
        User user = new User();
        User clonedUser = ObjectExtUtils.clone(user);

        Assert.assertNotEquals(user.hashCode(), clonedUser.hashCode());
    }
}
