package cn.chenzw.toolkit.sql.mysql.parser;

import cn.chenzw.toolkit.sql.core.context.SqlParserContext;
import cn.chenzw.toolkit.sql.core.meta.ColumnMetaData;
import cn.chenzw.toolkit.sql.core.meta.SqlMetaData;
import cn.chenzw.toolkit.sql.core.meta.TableMetaData;
import cn.chenzw.toolkit.sql.core.parser.AbstractSqlParser;
import cn.chenzw.toolkit.sql.exception.SqlParseException;
import cn.chenzw.toolkit.sql.mysql.meta.MySqlColumnMetaData;
import cn.chenzw.toolkit.sql.mysql.meta.MySqlSqlMetaData;
import cn.chenzw.toolkit.sql.mysql.meta.MySqlTableMetaData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author chenzw
 * @since 1.0.3
 */
@Slf4j
public class MySqlSqlParser extends AbstractSqlParser {

    private static final Pattern TABLE_NAME_PATTERN = Pattern.compile("create\\s*table\\s*(?=if\\s*not\\s*exists\\s*)?`?(.*?)`?\\s*\\(");

    private static final Pattern TABLE_COMMENT_PATTERN = Pattern.compile("comment\\s*=\\s*`(.*?)`");

    private static final Pattern COLUM_DEFINITION_PATTERN = Pattern.compile("\\(([\\s\\S]*)\\)");

    private static final Pattern COLUMN_PATTERN = Pattern.compile("^`(?<name>.*?)`\\s*(?<dataType>tinyint|smallint|mediumint|integer|int|bigint|float|double|decimal|datetime|date|year|timestamp|time|char|varchar|tinytext|text|mediumtext|longtext|blob|mediumblob|longblob)?(?:\\((?<size>\\d+)(\\s*,?\\s*(?<digits>\\d+))?\\))?\\s*(?<unsigned>unsigned)?\\s*(character\\s*set\\s*(.*?)\\s*collate\\s*(.*?))?\\s*(?<nullable>not null|null)?\\s*(?<autoIncrement>auto_increment)?\\s*(?:default\\s*(?<defaultValue>\\S+))?\\s*(?:comment\\s*`(?<comment>.*?)`)?");

    private static final Pattern PRIMARY_KEY_PATTERN = Pattern.compile("primary\\s*key\\s*\\(`(.*?)`\\)");

    @Override
    protected void preParse(SqlParserContext parserContext) throws SqlParseException {
        String createTableSql = parserContext.getCreateTableSql();
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
                .replaceAll("\\\\", "").toLowerCase();

        parserContext.setCreateTableSql(createTableSql);
    }

    @Override
    protected TableMetaData parseTableMeta(SqlParserContext parserContext) throws SqlParseException {
        String createTableSql = parserContext.getCreateTableSql();

        //log.info("SQL => {}", createTableSql);

        String tableName = findMatchingValue(TABLE_NAME_PATTERN, createTableSql);
        String tableComment = findMatchingValue(TABLE_COMMENT_PATTERN, createTableSql);

        return new MySqlTableMetaData(tableName, tableComment);
    }

    @Override
    protected List<? extends ColumnMetaData> parseColumnMeta(SqlParserContext parserContext) throws SqlParseException {
        String createTableSql = parserContext.getCreateTableSql();

        String columnDefinitionContent = findMatchingValue(COLUM_DEFINITION_PATTERN, createTableSql);

        // 字段分离
        String[] columnDefinitions = columnDefinitionContent.split(",\\n|,\\r\\n");

        List<MySqlColumnMetaData> columnMetaDatas = new ArrayList<>();
        for (String columnDefinition : columnDefinitions) {

            Matcher matcher = COLUMN_PATTERN.matcher(columnDefinition.trim());
            if (matcher.find()) {
                MySqlColumnMetaData columnMetaData = new MySqlColumnMetaData();
                columnMetaData.setColumnName(matcher.group("name"));
                columnMetaData.setDataType(matcher.group("dataType"));
                columnMetaData.setRemarks(matcher.group("comment"));
                columnMetaData.setColumnSize(matcher.group("size") == null ?
                        null : Integer.valueOf(matcher.group("size")));
                columnMetaData.setDecimalDigits(matcher.group("digits") == null ?
                        null : Integer.valueOf(matcher.group("digits")));

                String autoIncrement = matcher.group("autoIncrement");
                if (autoIncrement != null) {
                    columnMetaData.setAutoIncrement(true);
                }

                String nullable = matcher.group("nullable");
                if (nullable != null) {
                    if ("null".equalsIgnoreCase(nullable)) {
                        columnMetaData.setNullable(true);
                    }
                    if ("not null".equalsIgnoreCase(nullable)) {
                        columnMetaData.setNullable(false);
                    }
                }

                columnMetaData.setColumnDef(matcher.group("defaultValue"));
                columnMetaDatas.add(columnMetaData);
            }

            // 设置主键
            String primaryKey = findMatchingValue(PRIMARY_KEY_PATTERN, columnDefinition.trim());
            if (StringUtils.isNotBlank(primaryKey)) {
                columnMetaDatas.stream().forEach((data) -> {
                    if (Objects.equals(primaryKey, data.getColumnName())) {
                        data.setPrimaryKey(true);
                    }
                });
            }
        }
        return columnMetaDatas;
    }

    @Override
    public SqlMetaData createSqlMetaData() {
        return new MySqlSqlMetaData();
    }

    private String findMatchingValue(Pattern pattern, String sql) {
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }


    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("`(?<name>.*?)`\\s*(?<dataType>int|bigint|varchar)?(\\(\\d+\\))?\\s*(?<unsigned>unsigned)?\\s*(?<notNull>not null)?\\s*comment\\s*`(?<comment>.*?)`");

        Matcher matcher = pattern.matcher(" `duty_id` bigint(20) unsigned not null comment `值班id`");
        if (matcher.find()) {
            System.out.println(matcher.group("dataType"));
            System.out.println(matcher.group("name"));
            System.out.println(matcher.group("unsigned"));
            System.out.println(matcher.group("notNull"));
            System.out.println(matcher.group("comment"));

        }

    }
}
