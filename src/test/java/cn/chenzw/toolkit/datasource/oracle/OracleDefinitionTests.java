package cn.chenzw.toolkit.datasource.oracle;

import cn.chenzw.toolkit.datasource.entity.ColumnDefinition;
import cn.chenzw.toolkit.datasource.entity.TableDefinition;
import cn.chenzw.toolkit.datasource.oracle.builder.OracleColumnDefinitionBuilder;
import cn.chenzw.toolkit.datasource.oracle.builder.OracleTableDefinitionBuilder;
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
@ActiveProfiles(value = "oracle")
public class OracleDefinitionTests {

    @Autowired
    DataSource dataSource;

    @Test
    public void test() throws SQLException {

        Connection connection = dataSource.getConnection();

        List<ColumnDefinition> columnDefinitions = new OracleColumnDefinitionBuilder(connection, "DFCC_PROJ_STEP_INST")
                .build();

        System.out.println(columnDefinitions);

        connection.close();
    }

    @Test
    public void testTableDefinition() throws SQLException {
        Connection connection = dataSource.getConnection();

        TableDefinition tableDefinition = new OracleTableDefinitionBuilder(connection, "STAFF").build();

        System.out.println(tableDefinition);

        connection.close();
    }
}
