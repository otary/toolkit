package cn.chenzw.toolkit.wp.raw.response;

import lombok.Data;

/**
 * @author chenzw
 */
@Data
public class XunLeiCaptchaTokenResponse {

    private String captcha_token;

    private Long expires_in;
}
