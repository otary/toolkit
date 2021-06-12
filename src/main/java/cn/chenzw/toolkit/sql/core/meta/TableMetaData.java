package cn.chenzw.toolkit.sql.core.meta;

import java.util.Map;

/**
 * 表格
 *
 * @author chenzw
 */
public interface TableMetaData {

    /**
     * 表名
     *
     * @return
     */
    String getTableName();

    /**
     * 备注
     *
     * @return
     */
    String getRemarks();


    /**
     * 扩展属性
     *
     * @return
     */
    Map<String, Object> getExt();

    void setExt(Map<String, Object> extMap);
}

