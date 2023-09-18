package cn.chenzw.toolkit.wp.raw.response;

import lombok.Data;

/**
 * @author chenzw
 */
@Data
public class QuarkShareInfoResponse {

    private Integer status;

    private Integer code;

    private String message;

    private Integer timestamp;

    private ShareData data;

    private ShareAuthor author;

    @Data
    public static class ShareData {

        private Boolean subscribed;

        private String stoken;

        private Integer expired_type;

        private String expired_at;

        private String title;

        private Long expired_days;

    }

    @Data
    public static class ShareAuthor {

        private String avatar_url;

        private String nick_name;
    }
}
