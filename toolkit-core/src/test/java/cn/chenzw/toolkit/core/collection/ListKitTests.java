package cn.chenzw.toolkit.core.collection;

import cn.chenzw.toolkit.core.domain.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(JUnit4.class)
public class ListKitTests {

    @Test
    public void testJoinFieldValue() throws NoSuchFieldException, IllegalAccessException {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("zhangsan" + i);

            users.add(user);
        }
        String ids = ListKit.joinFieldValue(users, "id", "#");
        Assert.assertEquals("0#1#2#3#4#5#6#7#8#9", ids);

        String ids2 = ListKit.joinFieldValue(users, "id");
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
        Assert.assertTrue(ListKit.contains(users, kvMap));


        Map<String, Object> kvMap2 = new HashMap() {
            {
                put("id", "3");  //类型不一致
            }
        };
        // 类型不一致，不存在
        Assert.assertFalse(ListKit.contains(users, kvMap2));

        // 单字段匹配
        Assert.assertTrue(ListKit.contains(users, "id", 3));
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
        List<User> foundUsers = ListKit.find(users, kvMap);
        Assert.assertEquals("[User{id=3, name='zhangsan3', sex='null', age=null, birthDate=null}]", foundUsers.toString());

        // 返回第一个匹配的元素
        User foundUser = ListKit.findFirst(users, kvMap);
        Assert.assertEquals("User{id=3, name='zhangsan3', sex='null', age=null, birthDate=null}", foundUser.toString());

    }

    /**
     * 交集
     */
    @Test
    public void testIntersection() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("zhangsan" + i);

            users.add(user);
        }

        List<User> users2 = new ArrayList<>();
        User user21 = new User();
        user21.setId(2);
        user21.setName("zhangsan");
        users2.add(user21);

        User user22 = new User();
        user22.setId(4);
        user22.setName("zhangsan");
        users2.add(user22);

        User user23 = new User();
        user23.setId(12);
        user23.setName("zhangsan");
        users2.add(user23);

        List<User> intersectionList = ListKit.intersection(users, users2, (user, user2) -> {
            if (user.getId() == user2.getId()) {
                return true;
            }
            return false;
        });

        Assert.assertEquals("[User{id=2, name='zhangsan2', sex='null', age=null, birthDate=null}, User{id=4, name='zhangsan4', sex='null', age=null, birthDate=null}]",
                intersectionList.toString());
    }


    /**
     * 差集
     */
    @Test
    public void testSubtract() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(i);
            user.setName("zhangsan" + i);
            users.add(user);
        }

        List<User> users2 = new ArrayList<>();
        User user21 = new User();
        user21.setId(2);
        user21.setName("zhangsan");
        users2.add(user21);

        User user22 = new User();
        user22.setId(4);
        user22.setName("zhangsan");
        users2.add(user22);

        User user23 = new User();
        user23.setId(12);
        user23.setName("zhangsan");
        users2.add(user23);

        List<User> subtractList = ListKit.subtract(users, users2, (user, user2) -> {
            if (user.getId() == user2.getId()) {
                return true;
            }
            return false;
        });


        Assert.assertEquals("[User{id=0, name='zhangsan0', sex='null', age=null, birthDate=null}, User{id=1, name='zhangsan1', sex='null', age=null, birthDate=null}, User{id=3, name='zhangsan3', sex='null', age=null, birthDate=null}]",
                subtractList.toString());
    }


    /**
     * 去重
     */
    @Test
    public void testUnique() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setId(i);
            user.setName("zhangsan" + i);

            users.add(user);
        }

        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setId(i);
            user.setName("zhangsan" + i * 2);

            users.add(user);
        }
        List<User> uniqueList = ListKit.unique(users, user -> user.getId());

        Assert.assertEquals("[User{id=0, name='zhangsan0', sex='null', age=null, birthDate=null}, User{id=1, name='zhangsan1', sex='null', age=null, birthDate=null}, User{id=2, name='zhangsan2', sex='null', age=null, birthDate=null}]",
                uniqueList.toString());
    }
}
