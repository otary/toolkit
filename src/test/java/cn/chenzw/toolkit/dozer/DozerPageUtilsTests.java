package cn.chenzw.toolkit.dozer;

import cn.chenzw.toolkit.domain.dto.UserDto;
import cn.chenzw.toolkit.domain.entity.User;
import com.github.pagehelper.Page;
import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class DozerPageUtilsTests {

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
        List<UserDto> userDtos = DozerPageUtils.mapList(new DozerBeanMapper(), users, UserDto.class);

        Assert.assertTrue(userDtos instanceof Page);
        Assert.assertEquals(5, ((Page) userDtos).getTotal());
    }
}
