package cn.chenzw.toolkit.mybatis.multiple.support;


import cn.chenzw.toolkit.mybatis.multiple.annotation.EnableMultipleDataSource;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@EnableMultipleDataSource
@SpringBootTest(classes = DataSourceHolderTests.class)
public class DataSourceHolderTests {

    @Test
    @Ignore
    public void testGetPrimary(){
        DataSource primaryDataSource = DataSourceHolder.getInstance().getPrimary();
        System.out.println(primaryDataSource);
    }


    public static void main(String[] args) {
        SpringApplication.run(DataSourceHolder.class, args);
    }

}
