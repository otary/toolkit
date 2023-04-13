package cn.chenzw.toolkit.db.sql.parser;

import cn.chenzw.toolkit.db.sql.metadata.CreateTableSQL;
import cn.chenzw.toolkit.db.sql.parser.mysql.MySqlSQLParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@Slf4j
@RunWith(JUnit4.class)
public class SQLParserTests {

    private ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Test
    public void testParseCreateTableSQL() {
        SQLParser sqlParser = new MySqlSQLParser();
        CreateTableSQL createTableSQL = sqlParser.parseCreateTableSql("CREATE TABLE `quotation`  (\n" +
                "  `qid` bigint unsigned NOT NULL COMMENT 'ID',\n" +
                "  `topic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主题',\n" +
                "  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '语录内容',\n" +
                "  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',\n" +
                "  `update_time` date NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',\n" +
                "  `likes_count` datetime NOT NULL DEFAULT 0 COMMENT '点赞次数',\n" +
                "  `collect_count` int(0) NOT NULL DEFAULT 0 COMMENT '收藏数量',\n" +
                "  `view_count` int(0) NOT NULL DEFAULT 0 COMMENT '查看次数',\n" +
                "  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'PUBLISH' COMMENT '状态',\n" +
                "  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'chenzw' COMMENT '作者',\n" +
                "  `meta1` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展属性1',\n" +
                "  PRIMARY KEY (`qid`) USING BTREE,\n" +
                "  UNIQUE INDEX `UDX_CONTENT`(`topic`, `content`) USING BTREE,\n" +
                "  INDEX `IDX_TOPIC`(`topic`) USING BTREE\n" +
                ") ENGINE = InnoDB AUTO_INCREMENT = 5492 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic STORAGE DISK;");

        log.info("CreateTableSQL => {}", objectMapper.writeValueAsString(createTableSQL));
    }
}
