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
import org.apache.commons.collections.list.TreeList;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author chenzw
 * @since 1.0.3
 */
@Slf4j
public class MySqlSqlParser extends AbstractSqlParser {

    private Pattern tableNamePattern = Pattern.compile("create\\s*table\\s*(?=if\\s*not\\s*exists\\s*)?`?(.*?)`?\\s*\\(");

    private Pattern tableCommentPattern = Pattern.compile("comment\\s*=\\s*`(.*?)`");

    private Pattern columnDefinitionPattern = Pattern.compile("\\(([\\s\\S]*)\\)");


    @Override
    protected TableMetaData parseTableMeta(SqlParserContext parserContext) throws SqlParseException {
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

        log.info("SQL => {}", createTableSql);

        String tableName = findMatchingValue(tableNamePattern, createTableSql);
        String tableComment = findMatchingValue(tableCommentPattern, createTableSql);

        return new MySqlTableMetaData(tableName, tableComment);
    }

    @Override
    protected List<? extends ColumnMetaData> parseColumnMeta(SqlParserContext parserContext) throws SqlParseException {
        String createTableSql = parserContext.getCreateTableSql();

        String columnDefinitionContent = findMatchingValue(columnDefinitionPattern, createTableSql);
        String[] columnDefinitions = columnDefinitionContent.split(",");


//        for (String columnDefinition : columnDefinitions) {
//            columnDefinition
//        }

        List<MySqlColumnMetaData> columnMetaDatas = new ArrayList<>();

        columnMetaDatas.add(new MySqlColumnMetaData());

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
}
