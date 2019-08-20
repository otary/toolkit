package cn.chenzw.toolkit.datasource.mysql;

import cn.chenzw.toolkit.datasource.entity.ColumnDefinition;
import cn.chenzw.toolkit.datasource.entity.TableDefinition;
import cn.chenzw.toolkit.datasource.mysql.builder.MySqlTableDefinitionBuilder;
import cn.chenzw.toolkit.datasource.mysql.converter.MySqlTypeConverter;
import cn.chenzw.toolkit.spring.config.DataSourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class})
@WebAppConfiguration
@ActiveProfiles(value = "mysql")
public class MySqlTypeConverterTests {

    @Autowired
    DataSource dataSource;

    @Test
    public void test() throws SQLException {
        Connection connection = dataSource.getConnection();

        TableDefinition tableDefinition = new MySqlTableDefinitionBuilder(connection, "sys_user").build();
        List<ColumnDefinition> columnDefinitions = tableDefinition.getColumnDefinitions();
        MySqlTypeConverter mySqlTypeConverter = MySqlTypeConverter.getInstance();
        for (ColumnDefinition columnDefinition : columnDefinitions) {
            Class<?> type = mySqlTypeConverter.toJavaType(columnDefinition.getTypeName());
            System.out.println(type);
        }

        connection.close();
    }
}
