package cn.chenzw.toolkit.sql.mysql.parser;

import cn.chenzw.toolkit.sql.core.context.SqlParserContext;
import cn.chenzw.toolkit.sql.core.meta.SqlMetaData;
import cn.chenzw.toolkit.sql.core.parser.SqlParser;
import cn.chenzw.toolkit.sql.exception.SqlParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@Slf4j
@RunWith(JUnit4.class)
public class MySqlSqlParserTests {

    private ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Test
    public void testParse() throws SqlParseException, JsonProcessingException {
        SqlParser sqlParser = new MySqlSqlParser();
        SqlParserContext parserContext = new SqlParserContext();
        parserContext.setCreateTableSql("DROP TABLE IF EXISTS `duty_line`;\n" +
                "CREATE TABLE `duty_line`  (\n" +
                "  `duty_id` bigint(20) UNSIGNED NOT NULL COMMENT '值班id',\n" +
                "  `duty_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '值班线名称',\n" +
                "  `director` bigint(20) NOT NULL COMMENT '负责人',\n" +
                "  `director_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '负责人名称',\n" +
                "  `region_id` bigint(20) NOT NULL COMMENT '区域id',\n" +
                "  `business_id` bigint(20) NULL DEFAULT NULL COMMENT '所属系统id',\n" +
                "  `duty_type` int(1) NOT NULL DEFAULT 0 COMMENT '值班类型，0 手动， 1 自动',\n" +
                "  `is_remind_duty` int(1) NOT NULL DEFAULT 0 COMMENT '是否提醒排班 0否 1是(排班提醒，每周星期几发送下周排班给管理员)',\n" +
                "  `remind_duty_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '值班通知类型， week周 / month月',\n" +
                "  `remind_duty_day` int(11) NOT NULL DEFAULT 5 COMMENT '值班通知天数(排班提醒，每周星期几发送下周排班给管理员)',\n" +
                "  `is_follow_last_week` int(1) NOT NULL DEFAULT 0 COMMENT '是否按照上一周排班 0否 1是（若未排班，按照上一周排班）',\n" +
                "  `is_allow_adjust` int(1) NOT NULL DEFAULT 0 COMMENT '是否允许私下允许调班 0否 1是',\n" +
                "  `is_remote_duty` int(1) NOT NULL DEFAULT 0 COMMENT '是否允许远程值班 0否 1是',\n" +
                "  `remote_duty_type` int(11) NOT NULL DEFAULT 0 COMMENT '远程调班类型  0工作日， 1节假日， 2全部',\n" +
                "  `is_remind_vacation` int(1) NOT NULL DEFAULT 0 COMMENT '是否休假提醒 0否 1是',\n" +
                "  `remind_vacation_day` int(11) NOT NULL DEFAULT 5 COMMENT '休假提醒天数（每月月底前几天，可对排班人员进行休假填写）',\n" +
                "  `nextmonth_arrangement_state` int(1) NOT NULL DEFAULT 0 COMMENT '次月排班状态 0未排班，1排班成功，2排班异常',\n" +
                "  `nextmonth_arrangement_percent` decimal(5, 2) NOT NULL DEFAULT 0.00 COMMENT '次月排班百分比',\n" +
                "  `state` int(1) NOT NULL DEFAULT 1 COMMENT '状态：0禁用、1启用',\n" +
                "  `remark` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '备注',\n" +
                "  `template_id_used` bigint(20) NULL DEFAULT NULL COMMENT '手工排班-排班模版ID，使用的是哪个模版，只有当手动排班的时候才生效',\n" +
                "  `template_cycle_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手工排班-排班循环周期类型， day天 / month月；只有当手动排班的时候生效',\n" +
                "  `template_exclude_holiday` int(1) NULL DEFAULT 1 COMMENT '手工排班-排班是否排除节假日，0表示节假日要排班，1表示节假日不排班',\n" +
                "  `tempate_start_date` date NULL DEFAULT NULL COMMENT '手工排班-排班开始日期',\n" +
                "  `tempate_end_date` date NULL DEFAULT NULL COMMENT '手工排班-排班结束日期',\n" +
                "  `creator` bigint(20) NOT NULL COMMENT '创建者',\n" +
                "  `modifier` bigint(20) NOT NULL COMMENT '修改者',\n" +
                "  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建者名称',\n" +
                "  `modifier_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '修改者名称',\n" +
                "  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                "  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',\n" +
                "  `delete_token` int(1) NOT NULL DEFAULT 0 COMMENT '删除标志 1删除 0否',\n" +
                "  `business_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '所属系统名称',\n" +
                "  PRIMARY KEY (`duty_id`) USING BTREE,\n" +
                "  INDEX `idx_dutyline_region`(`region_id`) USING BTREE,\n" +
                "  INDEX `idx_dutyline_bussiness`(`business_id`) USING BTREE\n" +
                ") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '值班线信息' ROW_FORMAT = Dynamic;");
        SqlMetaData sqlMetaData = sqlParser.parse(parserContext);

        log.info("sqlMetaData => {}", objectMapper.writeValueAsString(sqlMetaData));
    }
}
