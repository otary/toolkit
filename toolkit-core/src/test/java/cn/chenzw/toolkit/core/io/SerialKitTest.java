package cn.chenzw.toolkit.core.io;

import cn.chenzw.toolkit.core.domain.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;

@RunWith(JUnit4.class)
public class SerialKitTest {

    @Test
    public void testBasic() {
        User user = new User();
        user.setName("张三");
        user.setId(1);
        user.setBirthDate(new Date(2019, 10, 10));
        user.setAge(20);

        // 序列化
        String serializedHexString = SerialKit.serializeAsHexString(user);
        Assert.assertEquals(
                "aced000573720029636e2e6368656e7a772e746f6f6c6b69742e636f72652e646f6d61696e2e656e746974792e55736572be5e7e32a5392cf10200054c00036167657400134c6a6176612f6c616e672f496e74656765723b4c00096269727468446174657400104c6a6176612f7574696c2f446174653b4c0002696471007e00014c00046e616d657400124c6a6176612f6c616e672f537472696e673b4c000373657871007e00037870737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870000000147372000e6a6176612e7574696c2e44617465686a81014b59741903000078707708000037f668c4a400787371007e000500000001740006e5bca0e4b88970",
                serializedHexString);

        // 反序列化
        User deserializeUser = SerialKit.deserializeFromHexString(serializedHexString);
        Assert.assertEquals((Integer) 1, deserializeUser.getId());
        Assert.assertEquals("张三", deserializeUser.getName());
    }

}
