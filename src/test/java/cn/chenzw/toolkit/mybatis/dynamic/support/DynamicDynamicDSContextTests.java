package cn.chenzw.toolkit.mybatis.dynamic.support;


import cn.chenzw.toolkit.mybatis.dynamic.annotation.EnableDynamicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@EnableDynamicDataSource
@SpringBootTest(classes = DynamicDynamicDSContextTests.class)
public class DynamicDynamicDSContextTests {

    @Test
   // @Ignore
    public void testGetPrimary(){
        DataSource primaryDataSource = DynamicDataSourceContext.getInstance().getPrimary();
        System.out.println(primaryDataSource);
    }


    public static void main(String[] args) {
        SpringApplication.run(DynamicDataSourceContext.class, args);
    }

}
