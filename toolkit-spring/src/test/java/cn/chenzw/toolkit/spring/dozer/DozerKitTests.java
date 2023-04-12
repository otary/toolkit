package cn.chenzw.toolkit.spring.dozer;

import cn.chenzw.toolkit.spring.domain.dto.UserDTO;
import cn.chenzw.toolkit.spring.domain.entity.User;
import cn.chenzw.toolkit.spring.dozer.core.DozerFieldMapping;
import com.github.dozermapper.core.CustomConverter;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(JUnit4.class)
public class DozerKitTests {

    @Test
    public void testMapList() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

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
        List<UserDTO> userList = DozerKit.mapList(mapper, users, UserDTO.class);

        Assert.assertEquals(5, userList.size());
        for (int i = 0; i < 5; i++) {
            UserDTO userDTO = userList.get(i);
            Assert.assertTrue(userDTO.getId().equals(i));
            Assert.assertEquals("张三", userDTO.getName());
            Assert.assertNotNull(userDTO.getBirthDate());
        }
    }

    @Test
    public void mapListWithConverter() {
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

        List<DozerFieldMapping> dozerFieldMappings = new ArrayList<>();
        dozerFieldMappings.add(new DozerFieldMapping("id", new CustomConverter() {
            @Override
            public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue,
                    Class<?> destinationClass, Class<?> sourceClass) {
                return 100 + (Integer) sourceFieldValue;
            }
        }));

        List<UserDTO> userList = DozerKit.mapList(users, UserDTO.class, dozerFieldMappings);
        // 每个id值都大于100
        Assert.assertThat(userList, Matchers.everyItem(
                Matchers.hasProperty("id", Matchers.greaterThanOrEqualTo(100)))
        );

    }
}
