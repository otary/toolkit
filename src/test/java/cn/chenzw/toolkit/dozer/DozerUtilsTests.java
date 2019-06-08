package cn.chenzw.toolkit.dozer;

import cn.chenzw.toolkit.domain.dto.UserDto;
import cn.chenzw.toolkit.domain.entity.User;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(JUnit4.class)
public class DozerUtilsTests {

    @Test
    public void testMapList() {
        Mapper mapper = new DozerBeanMapper();

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(i);
            user.setName("张三");
            user.setAge(i + 20);
            user.setSex("男");
            user.setBirthDate(new Date());

            users.add(user);
        }

        // 将List<User>转换成List<UserDto>
        List<UserDto> userDtos = DozerUtils.mapList(mapper, users, UserDto.class);

        Assert.assertEquals(userDtos.size(), 5);
        for (int i = 0; i < 5; i++) {
            UserDto userDto = userDtos.get(i);
            Assert.assertTrue(userDto.getId().equals(i));
            Assert.assertEquals(userDto.getName(), "张三");
            Assert.assertNotNull(userDto.getBirthDate());
        }
    }
}
