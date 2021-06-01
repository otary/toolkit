package cn.chenzw.toolkit.authentication.jwt;

import cn.chenzw.toolkit.authentication.jwt.entity.JwtEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@Slf4j
@RunWith(JUnit4.class)
public class JwtTokenUtilsTests {

    @Test
    public void testParseWithoutKey() {
        JwtEntity jwtEntity = JwtTokenUtils.parseWithoutKey("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");

        log.info("jwtEntity => {}", jwtEntity);
    }
}
