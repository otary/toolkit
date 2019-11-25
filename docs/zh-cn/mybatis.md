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