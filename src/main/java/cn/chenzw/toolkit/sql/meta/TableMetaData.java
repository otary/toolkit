package cn.chenzw.toolkit.sql.meta;

import lombok.Data;

/**
 * 表元数据
 * @author chenzw
 * @since 1.0.4
 */
@Data
public class TableMetaData {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 备注
     */
    private String remarks;
}
