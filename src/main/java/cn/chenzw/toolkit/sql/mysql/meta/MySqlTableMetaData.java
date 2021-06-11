package cn.chenzw.toolkit.sql.mysql.meta;

import cn.chenzw.toolkit.sql.core.meta.TableMetaData;

import java.util.Map;

/**
 * @author chenzw
 * @since 1.0.3
 */
public class MySqlTableMetaData implements TableMetaData {

    private String tableName;

    private String remarks;

    private Map<String, Object> ext;

    public MySqlTableMetaData(String tableName, String remarks) {
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
}
