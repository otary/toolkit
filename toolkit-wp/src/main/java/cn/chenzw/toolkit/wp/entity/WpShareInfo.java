package cn.chenzw.toolkit.wp.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author chenzw
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WpShareInfo {

    @Builder.Default
    private Integer fileCount = -1;

    @Builder.Default
    private Long fileSize = -1L;

    private String shareId;

    private String shareTitle;

    private String creatorId;

    private String creatorPhone;

    private String creatorAvatar;

    private String creatorName;

    @Builder.Default
    private Boolean needPassCode = Boolean.FALSE;

    private String passCode;

    private LocalDateTime expiration;

    private Date lastUpdateTime;

    @Builder.Default
    private Boolean valid = Boolean.TRUE;

    private String errMsg;

}
