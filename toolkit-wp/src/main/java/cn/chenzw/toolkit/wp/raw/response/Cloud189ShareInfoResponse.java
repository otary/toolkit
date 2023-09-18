package cn.chenzw.toolkit.wp.raw.response;

import lombok.Data;

/**
 * @author chenzw
 */
@Data
public class Cloud189ShareInfoResponse {

    private Integer res_code;

    private String res_message;

    private String accessCode;

    private Creator creator;

    private Integer expireTime;

    private Integer expireType;

    private String fileCreateDate;

    private String fileId;

    private String fileLastOpTime;

    private String fileName;

    private Integer fileSize;

    private String fileType;

    private Boolean isFolder;

    private Integer needAccessCode;

    private Integer reviewStatus;

    private Long shareDate;

    private Long shareId;

    private Integer shareMode;

    private Integer shareType;


    @Data
    public static class Creator {

        private String iconURL;

        private String nickName;

        private Boolean oper;

        private String ownerAccount;

        private Integer superVip;

        private Integer vip;

    }

}
