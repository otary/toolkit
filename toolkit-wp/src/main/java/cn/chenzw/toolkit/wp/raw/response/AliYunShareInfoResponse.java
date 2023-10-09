package cn.chenzw.toolkit.wp.raw.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenzw
 */
@Data
public class AliYunShareInfoResponse {

    private Integer file_count;

    private String share_name;

    private List<FileInfos> file_infos = new ArrayList<>();

    private String creator_phone;

    private String avatar;

    private String display_name;

    private Date updated_at;

    private String share_title;

    private Boolean is_creator_followable;

    private Boolean has_pwd;

    private String creator_id;

    private String creator_name;

    private String expiration;

    private String vip;


    @Data
    public static class FileInfos {

        private String type;

        private String file_id;

        private String file_name;

    }
}
