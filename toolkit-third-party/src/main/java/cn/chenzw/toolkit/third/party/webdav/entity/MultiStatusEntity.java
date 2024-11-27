package cn.chenzw.toolkit.third.party.webdav.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenzw
 */
@Data
public class MultiStatusEntity {

    private List<ResponseItem> items = new ArrayList<>();


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseItem {

        private String href;

        private String displayName;

        private String etag;

        private String contentType;

        private String owner;

        private Long contentLength;

        private Date lastModified;

        private String contentLanguage;

        private Date creationDate;

        private String type;

    }

}
