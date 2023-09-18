package cn.chenzw.toolkit.wp.raw.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author chenzw
 */
@Data
public class BaiduLocalsInfo {

    private String csrf;

    private Integer uk;

    private String username;

    private Integer loginstate;

    private String skinName;

    private String bdstoken;

    private String photo;

    private Integer is_vip;

    private Integer is_svip;

    private Integer is_evip;

    private String now;

    private String XDUSS;

    private Integer curr_activity_code;

    private Integer show_vip_ad;

    private String share_photo;

    private String share_uk;

    private String shareid;

    private Boolean hit_ogc;

    private Integer expiredType;

    private Long ctime;

    private String description;

    private Integer followFlag;

    private Boolean access_list_flag;

    private Integer sharetype;

    private Integer view_visited;

    private Integer view_limit;

    private Integer owner_vip_level;

    private String owner_svip10_id;

    private Integer owner_vip_type;

    private String linkusername;

    private Integer errortype;

    private String share_page_type;

    private Integer errno;

    private Integer ufcTime;

    private Integer self;

    private Integer elink_self;

    @JsonProperty(value = "public")
    private Integer public2;
}
