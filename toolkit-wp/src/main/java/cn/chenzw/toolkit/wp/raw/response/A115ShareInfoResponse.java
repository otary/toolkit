package cn.chenzw.toolkit.wp.raw.response;

import lombok.Data;

import java.util.List;

/**
 * @author chenzw
 */
@Data
public class A115ShareInfoResponse {

    private Boolean state;

    private String error;

    private String errno;

    private ShareData data;

    @Data
    public static class UserInfo {

        private String user_id;

        private String user_name;

        private String face;

    }

    @Data
    public static class ShareInfo {

        private String snap_id;

        private Long file_size;

        private String share_title;

        private String share_state;

        private String forbid_reason;

        private Long create_time;

        private String receive_code;

        private String receive_count;

        private Long expire_time;

        private Integer file_category;

        private String auto_renewal;

        private String auto_fill_recvcode;

        private Integer can_report;

        private Integer can_notice;

        private Integer have_vio_file;

    }


    @Data
    public static class UserAppeal {

        private Integer can_appeal;

        private Integer can_share_appeal;

        private Integer popup_appeal_page;

        private Integer can_global_appeal;

    }

    @Data
    public static class ShareData {

        private UserInfo userinfo;

        private ShareInfo shareinfo;

        private Integer count;

        private String share_state;

        private UserAppeal user_appeal;

    }
}
