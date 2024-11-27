package cn.chenzw.toolkit.wp.raw.response;

import lombok.Data;

/**
 * @author chenzw
 */
@Data
public class UcShareInfoResponse {

    private Integer status;

    private Integer code;

    private String message;

    private Integer timestamp;

    private ShareData data;


    @Data
    public static class Author {

        private String member_type;

        private String avatar_url;

        private String nick_name;

    }

    @Data
    public static class ShareData {

        private Boolean subscribed;

        private String stoken;

        private Author author;

        private Integer expired_type;

        private Integer expired_at;

        private String title;

    }
}
