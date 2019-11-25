package cn.chenzw.toolkit.spring.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @Profile("mysql")
    public DataSource mysqlDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://rm-wz961udpxc1fn4u4lxo.mysql.rds.aliyuncs.com/shiro?characterEncoding=utf-8");

        basicDataSource.setUsername("chenzw");
        basicDataSource.setPassword("Chenzw_123");

        basicDataSource.setAccessToUnderlyingConnectionAllowed(true);
        return basicDataSource;
    }

    @Bean
    @Profile("oracle")
    public DataSource oracleDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@192.168.17.200:1521:devdb");

        basicDataSource.setUsername("bosswg_kf");
        basicDataSource.setPassword("bosswg");

        basicDataSource.setAccessToUnderlyingConnectionAllowed(true);
        return basicDataSource;
    }

    @Bean
    @Profile("h2")
    public DataSource h2DataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.h2.Driver");
        basicDataSource.setUrl("jdbc:h2:mem:test");
        basicDataSource.setUsername("sa");
        basicDataSource.setPassword("");

        return basicDataSource;
    }
}
