package cn.chenzw.toolkit.core.captcha;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 验证码构建器
 *
 * @author chenzw
 */
public class CaptchaBuilders {

    public static final CaptchaBuilder createDefault() {
        return new CaptchaBuilder();
    }

    public static final String randomAlphanumeric(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }
}
