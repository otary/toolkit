package cn.chenzw.toolkit.datasource.entity;

/**
 * 数据库信息封装
 * @author chenzw
 */
public class DatabaseDefinition {
    /**
     * 数据库名称
     */
    private String name;

    /**
     * 数据库类型
     */
    private String type;

    /**
     * 连接字符串
     */
    private String url;

    /**
     * 名称
     */
    private String driver;

    /**
     * 定义描述
     */
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
