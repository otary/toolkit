package cn.chenzw.toolkit.sql.core.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenzw
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SqlParserContext {

    private String createTableSql;


}
