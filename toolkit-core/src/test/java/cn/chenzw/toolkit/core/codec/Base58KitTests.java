package cn.chenzw.toolkit.core.codec;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.charset.StandardCharsets;

/**
 * @author chenzw
 */
@Slf4j
@RunWith(JUnit4.class)
public class Base58KitTests {

    @Test
    public void test() {
        String encoded = Base58Kit.encode("helloWorld".getBytes());
        Assert.assertEquals("6sBRWytaq3yjJ3", encoded);

        byte[] decoded = Base58Kit.decode(encoded);
        Assert.assertEquals("helloWorld", new String(decoded, StandardCharsets.UTF_8));
    }


}
