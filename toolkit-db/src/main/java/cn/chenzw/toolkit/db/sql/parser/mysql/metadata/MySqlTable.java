package cn.chenzw.toolkit.db.sql.parser.mysql.metadata;

import cn.chenzw.toolkit.db.sql.metadata.Table;

import java.util.Map;

/**
 * @author chenzw
 */
public class MySqlTable implements Table {

    private String tableName;

    private String remarks;

    private Map<String, Object> ext;

    public MySqlTable(String tableName, String remarks) {
        this.tableName = tableName;
        this.remarks = remarks;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public Map<String, Object> getExt() {
        return ext;
    }

    @Override
    public void setExt(Map<String, Object> extMap) {
        this.ext = extMap;
    }
}
