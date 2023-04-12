package cn.chenzw.toolkit.spring.dozer;

import cn.chenzw.toolkit.spring.domain.dto.UserDTO;
import cn.chenzw.toolkit.spring.domain.entity.User;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.pagehelper.Page;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class DozerPageKitTests {

    @Test
    public void testMapList() {
        Page<User> users = new Page<>(0, 5);
        users.setTotal(5);
        users.setPages(2);

        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(i);

            users.add(user);
        }
        List<UserDTO> userList = DozerPageKit.mapList(
                DozerBeanMapperBuilder.buildDefault(), users, UserDTO.class
        );

        Assert.assertTrue(userList instanceof Page);
        Assert.assertEquals(5, ((Page) userList).getTotal());
    }
}
