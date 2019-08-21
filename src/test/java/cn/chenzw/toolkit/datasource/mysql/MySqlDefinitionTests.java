package cn.chenzw.toolkit.datasource.mysql;

import cn.chenzw.toolkit.datasource.entity.TableDefinition;
import cn.chenzw.toolkit.datasource.mysql.builder.MySqlTableDefinitionBuilder;
import cn.chenzw.toolkit.datasource.oracle.builder.OracleTableDefinitionBuilder;
import cn.chenzw.toolkit.spring.config.DataSourceConfig;
import org.junit.Assert;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class})
@WebAppConfiguration
@ActiveProfiles(value = "mysql")
public class MySqlDefinitionTests {

    @Autowired
    DataSource dataSource;

    @Test
    public void testTableDefinition() throws SQLException {
        Connection connection = dataSource.getConnection();
        TableDefinition tableDefinition = new MySqlTableDefinitionBuilder(connection, "sys_user").build();

        Assert.assertNotNull(tableDefinition);
        Assert.assertTrue(tableDefinition.getColumnDefinitions().size() > 0);

        System.out.println(tableDefinition);
        connection.close();
    }
}
