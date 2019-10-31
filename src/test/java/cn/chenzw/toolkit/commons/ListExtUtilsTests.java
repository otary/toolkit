package cn.chenzw.toolkit.commons;

import cn.chenzw.toolkit.domain.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void testContains() throws NoSuchFieldException, IllegalAccessException {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("zhangsan" + i);

            users.add(user);
        }

        Map<String, Object> kvMap = new HashMap() {
            {
                put("id", 3);
                put("name", "zhangsan3");
            }
        };
        // 存在
        Assert.assertTrue(ListExtUtils.contains(users, kvMap));


        Map<String, Object> kvMap2 = new HashMap() {
            {
                put("id", "3");  //类型不一致
            }
        };
        // 类型不一致，不存在
        Assert.assertFalse(ListExtUtils.contains(users, kvMap2));

        // 单字段匹配
        Assert.assertTrue(ListExtUtils.contains(users, "id", 3));
    }

    @Test
    public void testFind() throws NoSuchFieldException, IllegalAccessException {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("zhangsan" + i);

            users.add(user);
        }

        Map<String, Object> kvMap = new HashMap() {
            {
                put("id", 3);
                put("name", "zhangsan3");
            }
        };

        // 返回所有匹配的元素
        List<User> findedUsers = ListExtUtils.find(users, kvMap);
        Assert.assertEquals(findedUsers.toString(), "[User{id=3, name='zhangsan3', sex='null', age=null, birthDate=null}]");


        // 返回第一个匹配的元素
        User findedUser = ListExtUtils.findFirst(users, kvMap);
        Assert.assertEquals(findedUser.toString(), "User{id=3, name='zhangsan3', sex='null', age=null, birthDate=null}");

    }


}
