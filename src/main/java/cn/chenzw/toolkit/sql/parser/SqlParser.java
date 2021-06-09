package cn.chenzw.toolkit.sql.parser;


import cn.chenzw.toolkit.sql.meta.ColumnMetaData;
import cn.chenzw.toolkit.sql.meta.SqlMetaData;
import cn.chenzw.toolkit.sql.meta.TableMetaData;

/**
 * @author chenzw
 */
public interface SqlParser {


    /**
     * 前置处理
     *
     * @return
     */
    void preProcess();

    SqlMetaData parse();

    TableMetaData parseTableMeta();

    ColumnMetaData parseColumnMeta();

    void postProcess();


//    public SqlMetaData parse(String createTableSql) {
//
//        // 前置处理
//
//        // 解析表
//
//        // 解析字段
//
//        // 后置处理
//
//        return null;
//    }
}
