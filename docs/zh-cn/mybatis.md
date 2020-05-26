## mybatis


### SqlBatchMapperTemplate

批量执行模版

- 批量执行

``` java
@Autowired
SqlBatchMapperTemplate sqlBatchMapperTemplate;
    
List<User> users = new ArrayList();

// 批量提交（提高性能）
sqlBatchMapperTemplate.execute(UserMapper.class, (userMapper)-> userMapper.save(users));
    
```

### 辅助注解
- `@MyBatisRepository`
- `@TkMyBatisRepository`

``` java
import cn.chenzw.toolkit.mybatis.core.support.TkMyBatisRepository;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackages = {"cn.chenzw.dial.test"}, annotationClass = TkMyBatisRepository.class)
public class MyBatisConfig {

}
```

### 多数据源

``` java
import cn.chenzw.toolkit.mybatis.dynamic.annotation.EnableDynamicDataSource;

@Configuration
@EnableMultipleDataSource
public class DataSourceConfig {

}

public void test() {
    // 获取主数据源
    DataSource primaryDataSource = DataSourceContext.getInstance().getPrimary();
    
    // 获取所有的数据源
    // Map<String, DataSource> dataSources = DataSourceContext.getInstance().list();
    
    // 获取指定数据源的Mapper
    UserMapper userMapper = SqlMapperUtils.getMapper(dataSource, UserMapper.class);
}

```

``` yaml
spring:
  datasource:
    druid:
      first:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: org.h2.Driver
        url: jdbc:h2:mem:test
        username: sa
        password:
      second:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: org.h2.Driver
        url: jdbc:h2:mem:test
        username: sa
        password:
```
