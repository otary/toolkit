package cn.chenzw.toolkit.core.net.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzw
 */
@Data
public class HTMLMetadata {

    private String title;

    private String keywords;

    private String description;

    private String appName;

    private List<Icon> appleIcons = new ArrayList<>();

    private List<Icon> icons = new ArrayList<>();

    private List<RSS> rssXMLs = new ArrayList();

    private List<RSS> atomXMLs = new ArrayList<>();

    private Object doc;

    @Data
    public static class Icon {

        private String sizes;

        private String url;

    }

    @Data
    public static class RSS {

        private String title;

        private String url;
    }
}
