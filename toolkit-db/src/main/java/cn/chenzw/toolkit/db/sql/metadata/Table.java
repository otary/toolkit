package cn.chenzw.toolkit.db.sql.metadata;

import java.util.Map;

/**
 * 表格
 *
 * @author chenzw
 */
public interface Table {

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

}

