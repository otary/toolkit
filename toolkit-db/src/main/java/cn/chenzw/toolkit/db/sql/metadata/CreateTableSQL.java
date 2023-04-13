package cn.chenzw.toolkit.db.sql.metadata;

import java.util.List;

/**
 * @author chenzw
 */
public interface CreateTableSQL {

    Table getTableMetadata();

    List<? extends Column> getColumnsMetadata();
}
