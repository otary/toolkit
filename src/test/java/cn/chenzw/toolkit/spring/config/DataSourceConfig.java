package cn.chenzw.toolkit.spring.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://rm-wz961udpxc1fn4u4lxo.mysql.rds.aliyuncs.com/shiro?characterEncoding=utf-8");

        basicDataSource.setUsername("chenzw");
        basicDataSource.setPassword("Chenzw_123");
        return basicDataSource;
    }
}
