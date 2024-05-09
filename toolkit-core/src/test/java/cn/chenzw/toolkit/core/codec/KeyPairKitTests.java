package cn.chenzw.toolkit.core.codec;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.StringReader;
import java.security.KeyPair;

/**
 * @author chenzw
 */
@Slf4j
@RunWith(JUnit4.class)
public class KeyPairKitTests {

    @Test
    public void test() throws IOException {
        // 创建KeyPair
        KeyPair keyPair = KeyPairKit.createKeyPair(512);

        // 生成PEM字符串
        String PEMString = KeyPairKit.buildPEMString(keyPair);
        log.info("PEM => {}", PEMString);

        // 读取PEM
        try(StringReader sr = new StringReader(PEMString)) {
            KeyPair keyPair2 = KeyPairKit.readKeyPair(sr);
            log.info("keyPair => {}", keyPair2);
        }
    }
}
