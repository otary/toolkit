package cn.chenzw.toolkit.db.sql.parser.mysql;

import cn.chenzw.toolkit.core.util.RegexKit;
import cn.chenzw.toolkit.db.sql.exception.SqlParseException;
import cn.chenzw.toolkit.db.sql.metadata.Column;
import cn.chenzw.toolkit.db.sql.metadata.CreateTableSQL;
import cn.chenzw.toolkit.db.sql.metadata.Table;
import cn.chenzw.toolkit.db.sql.parser.CreateTableSQLParser;
import cn.chenzw.toolkit.db.sql.parser.mysql.metadata.MySqlColumn;
import cn.chenzw.toolkit.db.sql.parser.mysql.metadata.MySqlCreateTableSQL;
import cn.chenzw.toolkit.db.sql.parser.mysql.metadata.MySqlTable;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author chenzw
 */
public class MySqlCreateTableSQLParser implements CreateTableSQLParser {

    private static final Pattern TABLE_NAME_PATTERN = Pattern.compile("create\\s*table\\s*(?=if\\s*not\\s*exists\\s*)?`?(.*?)`?\\s*\\(");

    private static final Pattern TABLE_COMMENT_PATTERN = Pattern.compile("comment\\s*=\\s*`(.*?)`");

    private static final Pattern COLUM_DEFINITION_PATTERN = Pattern.compile("\\(([\\s\\S]*)\\)");

    private static final Pattern COLUMN_PATTERN = Pattern.compile("^`(?<name>.*?)`\\s*(?<dataType>tinyint|smallint|mediumint|integer|int|bigint|bit|float|real|double|decimal|numeric|smallmoney|money|smalldatetime|datetime|date|year|timestamp|time|varbinary|nvarchar|nchar|char|varchar|tinytext|text|mediumtext|longtext|blob|mediumblob|longblob|binary|image)+(?:\\((?<size>\\d+)(\\s*,?\\s*(?<digits>\\d+))?\\))?\\s*(?<unsigned>unsigned)?\\s*(?:character\\s*set\\s*(.*)\\s*collate\\s*(\\w*))?\\s*(?<nullable>not null|null)?\\s*(?<autoIncrement>auto_increment)?\\s*(?:default\\s*(?<defaultValue>\\S+))?\\s*(on update current_timestamp\\(0\\))?\\s*(?=comment\\s*`(?<comment>.*?)`)?");

    private static final Pattern PRIMARY_KEY_PATTERN = Pattern.compile("primary\\s*key\\s*\\(`(.*?)`\\)");


    @Override
    public CreateTableSQL parse(String createTableSql) throws SqlParseException {
        if (StringUtils.isBlank(createTableSql)) {
            throw new SqlParseException("SQL为空!");
        }

        // 处理特殊字符
        createTableSql = createTableSql.trim().replaceAll("'", "`")
                .replaceAll("\"", "`")
                .replaceAll("，", ",")
                .replaceAll("\\\\n`", "")
                .replaceAll("\\+", "")
                .replaceAll("``", "`")
                .replaceAll("\\\\", "")
                .toLowerCase();

        return new MySqlCreateTableSQL(
                parseTableMetadata(createTableSql),
                parseColumnsMetadata(createTableSql)
        );
    }

    protected Table parseTableMetadata(String createTableSql) {
        String tableName = RegexKit.getGroup1(TABLE_NAME_PATTERN, createTableSql);
        String tableComment = RegexKit.getGroup1(TABLE_COMMENT_PATTERN, createTableSql);
        return new MySqlTable(tableName, tableComment);
    }

    protected List<? extends Column> parseColumnsMetadata(String createTableSql) throws SqlParseException {
        String columnContent = RegexKit.getGroup1(COLUM_DEFINITION_PATTERN, createTableSql);

        if (StringUtils.isBlank(columnContent)) {
            return Collections.emptyList();
        }
        // 字段分离
        String[] columnDefinitions = columnContent.split(",\\n|,\\r\\n");
        List<MySqlColumn> columns = Arrays.stream(columnDefinitions).map((columnDefinition) -> {
                    Matcher matcher = COLUMN_PATTERN.matcher(columnDefinition.trim());
                    if (matcher.find()) {
                        MySqlColumn column = new MySqlColumn();
                        column.setName(matcher.group("name"));
                        column.setDataType(matcher.group("dataType"));
                        column.setRemarks(matcher.group("comment"));
                        column.setSize(
                                matcher.group("size") == null ? null : Integer.valueOf(matcher.group("size"))
                        );
                        column.setDecimalDigits(
                                matcher.group("digits") == null ? null : Integer.valueOf(matcher.group("digits"))
                        );
                        String autoIncrement = matcher.group("autoIncrement");
                        if (autoIncrement != null) {
                            column.setAutoIncrement(true);
                        }

                        String nullable = matcher.group("nullable");
                        if (nullable != null) {
                            if ("null".equalsIgnoreCase(nullable)) {
                                column.setNullable(true);
                            }
                            if ("not null".equalsIgnoreCase(nullable)) {
                                column.setNullable(false);
                            }
                        }
                        column.setDefaultValue(matcher.group("defaultValue"));
                        return column;
                    }
                    return null;
                })
                .filter((column) -> column != null)
                .collect(Collectors.toList());

        // 设置主键
        Arrays.stream(columnDefinitions).forEach((columnDefinition) -> {
            String primaryKeyColName = RegexKit.getGroup1(PRIMARY_KEY_PATTERN, columnDefinition.trim());
            if (StringUtils.isNotBlank(primaryKeyColName)) {
                columns.stream().forEach((column) -> {
                    if (Objects.equals(primaryKeyColName, column.getName())) {
                        column.setPrimaryKey(true);
                    }
                });
            }
        });
        return columns;
    }
}
