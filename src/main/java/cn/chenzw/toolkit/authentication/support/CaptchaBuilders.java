package cn.chenzw.toolkit.authentication.support;

import org.apache.commons.lang3.RandomStringUtils;

/**
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
