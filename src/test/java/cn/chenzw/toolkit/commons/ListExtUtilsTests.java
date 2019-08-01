package cn.chenzw.toolkit.commons;

import cn.chenzw.toolkit.domain.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class ListExtUtilsTests {

    @Test
    public void testJoinFieldValue() throws NoSuchFieldException, IllegalAccessException {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("zhangsan" + i);

            users.add(user);
        }
        String ids = ListExtUtils.joinFieldValue(users, "id", "#");
        Assert.assertEquals("0#1#2#3#4#5#6#7#8#9", ids);

        String ids2 = ListExtUtils.joinFieldValue(users, "id");
        Assert.assertEquals("0,1,2,3,4,5,6,7,8,9", ids2);

    }

}
