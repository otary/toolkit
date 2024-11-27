package cn.chenzw.toolkit.wp.raw.response;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author chenzw
 */
@Data
public class XunLeiShareInfoResponse {

    private String share_status;

    private String share_status_text;

    private String file_num;

    private String expiration_left;

    private String expiration_left_seconds;

    private String expiration_at;

    private String restore_count_left;

    private List<File> files;

    private UserInfo user_info;

    private String next_page_token;

    private String pass_code_token;

    private String title;

    private String icon_link;

    private String thumbnail_link;

    private String contain_sensitive_resource_text;


    @Data
    public static class Link {

    }

    @Data
    public static class Audit {

        private String status;

        private String message;

        private String title;

    }

    @Data
    public static class Params {

        private String device_id;

        private String platform;

        private String platform_icon;

        private String tags;

    }

    @Data
    public static class File {

        private String kind;

        private String id;

        private String parent_id;

        private String name;

        private String user_id;

        private String size;

        private String revision;

        private String file_extension;

        private String mime_type;

        private Boolean starred;

        private String web_content_link;

        private String created_time;

        private String modified_time;

        private String icon_link;

        private String thumbnail_link;

        private String md5_checksum;

        private String hash;

        private Link links;

        private String phase;

        private Audit audit;


        private Boolean trashed;

        private String delete_time;

        private String original_url;

        private Params params;

        private Integer original_file_index;

        private String space;

        private Boolean writable;

        private String folder_type;

        private String collection;

        private String sort_name;

        private String user_modified_time;

        private String file_category;

        private String reference_resource;

    }

    @Data
    public static class UserInfo {

        private String user_id;

        private String portrait_url;

        private String nickname;

        private String avatar;

    }

    public boolean isSuccess() {
        return Objects.equals(share_status, "OK");
    }
}
