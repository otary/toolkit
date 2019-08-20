package cn.chenzw.toolkit.datasource.core;

import cn.chenzw.toolkit.datasource.constants.DbConstants;
import cn.chenzw.toolkit.datasource.entity.ColumnDefinition;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzw
 */
public abstract class AbstractColumnDefinitionBuilder {

    private Connection connection;
    private String tableName;

    public AbstractColumnDefinitionBuilder(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
    }


    protected String getColumnName(ResultSet rs) throws SQLException {
        return rs.getString(DbConstants.RS_COLUMN_NAME);
    }

    protected String getTypeName(ResultSet rs) throws SQLException {
        return rs.getString(DbConstants.RS_TYPE_NAME);
    }

    protected Integer getColumnSize(ResultSet rs) throws SQLException {
        return rs.getInt(DbConstants.RS_COLUMN_SIZE);
    }

    protected String getRemarks(ResultSet rs) throws SQLException {
        return rs.getString(DbConstants.RS_REMARKS);
    }


    protected String getPrimaryKey(ResultSet rs) throws SQLException {
        return rs.getString(DbConstants.RS_COLUMN_NAME);
    }

    protected String getForeignKey(ResultSet rs) throws SQLException {
        return rs.getString(DbConstants.RS_FK_COLUMN_NAME);
    }

    protected Boolean isNullable(ResultSet rs) throws SQLException {
        return rs.getBoolean(DbConstants.RS_IS_NULLABLE);
    }

    protected String getColumnDef(ResultSet rs) throws SQLException {
        return rs.getString(DbConstants.RS_COLUMN_DEF);
    }

    protected Integer getDecimalDigits(ResultSet rs) throws SQLException {
        return rs.getInt(DbConstants.RS_DECIMAL_DIGITS);
    }

    public List<ColumnDefinition> build() throws SQLException {
        ResultSet columnRs = connection.getMetaData().getColumns(null, null, tableName, null);

        List<ColumnDefinition> columnDefinitions = new ArrayList<>();
        while (columnRs.next()) {
            columnDefinitions
                    .add(new ColumnDefinition(getColumnName(columnRs), getTypeName(columnRs), getColumnSize(columnRs),
                            getDecimalDigits(columnRs), getRemarks(columnRs), null, null, isNullable(columnRs),
                            getColumnDef(columnRs)));
        }

        ResultSet primaryKeyRs = connection.getMetaData().getPrimaryKeys(null, null, tableName);
        setPrimaryKey(primaryKeyRs, columnDefinitions);

        ResultSet importedKeyRs = connection.getMetaData().getImportedKeys(null, null, tableName);
        setForeignKey(importedKeyRs, columnDefinitions);

        return columnDefinitions;
    }

    private void setPrimaryKey(ResultSet rs, List<ColumnDefinition> columnDefinitions) throws SQLException {
        while (rs.next()) {
            String primaryKey = getPrimaryKey(rs);

            for (ColumnDefinition columnDefinition : columnDefinitions) {
                if (columnDefinition.getColumnName().equals(primaryKey)) {
                    columnDefinition.setPrimaryKey(true);
                } else {
                    columnDefinition.setPrimaryKey(false);
                }
            }
        }
    }

    private void setForeignKey(ResultSet rs, List<ColumnDefinition> columnDefinitions) throws SQLException {
        while (rs.next()) {
            String foreignKey = getForeignKey(rs);

            for (ColumnDefinition columnDefinition : columnDefinitions) {
                if (columnDefinition.getColumnName().equals(foreignKey)) {
                    columnDefinition.setPrimaryKey(true);
                } else {
                    columnDefinition.setPrimaryKey(false);
                }
            }
        }
    }
}
