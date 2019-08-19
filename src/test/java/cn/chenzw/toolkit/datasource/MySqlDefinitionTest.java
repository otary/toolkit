package cn.chenzw.toolkit.datasource;

import cn.chenzw.toolkit.datasource.entity.TableDefinition;
import cn.chenzw.toolkit.spring.config.DataSourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class})
@WebAppConfiguration
public class MySqlDefinitionTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void testDataSource() throws SQLException {

        Connection connection = dataSource.getConnection();

        System.out.println(connection.getMetaData().getDriverName().toUpperCase());


        ResultSet tables = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
        while (tables.next()){

        }

        TableDefinition tableDefinition = new TableDefinition();
        tableDefinition.setTableName();
    }
}
