package cn.chenzw.toolkit.core.jwt;

import cn.chenzw.toolkit.core.jwt.entity.JWTEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@Slf4j
@RunWith(JUnit4.class)
public class JWTTokenKitTests {

    @Test
    public void testParseWithoutKey() {
        JWTEntity jwtEntity = JWTTokenKit.parseWithoutKey("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");

        log.info("jwtEntity => {}", jwtEntity);
    }
}
