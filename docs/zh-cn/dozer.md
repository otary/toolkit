## dozer

dozer工具类

### DozerUtils

- 拷贝对象属性

``` java
// 将List<User>转换成List<UserDto>
List<UserDto> userDtos = DozerUtils.mapList(mapper, users, UserDto.class);
```

- 支持自定义字段转换

``` java
List<User> users = new ArrayList<>();
for (int i = 0; i < 5; i++) {
   User user = new User();
   user.setId(i);
   user.setName("张三");

   users.add(user);
}

DozerBeanMapper mapper = new DozerBeanMapper();
List<DozerFieldMapping> dozerFieldMappings = new ArrayList<>();
dozerFieldMappings.add(new DozerFieldMapping("id", new CustomConverter() {
     @Override
     public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        return 100 + (Integer) sourceFieldValue;  // id值都加100
     }
}));

List<UserDto> userDtos = DozerUtils.mapList(mapper, users, UserDto.class, dozerFieldMappings); // 所有的id值都大于100
```

### DozerPageUtils

- 兼容com.github.pagehelper.Page的对象转换工具

``` java
Page<User> users = new Page<>(0, 5);
users.setTotal(5);
users.setPages(2);

for (int i = 0; i < 5; i++) {
    User user = new User();
    user.setId(i);
    users.add(user);
}

List<UserDto> userDtos = DozerPageUtils.mapList(new DozerBeanMapper(), users, UserDto.class);  // => Page{count=true, pageNum=0, pageSize=5, startRow=0, endRow=0, total=5, pages=2, reasonable=null, pageSizeZero=null}[User{id=0, name='null', sex='null', age=null, birthDate=null}, User{id=1, name='null', sex='null', age=null, birthDate=null}, User{id=2, name='null', sex='null', age=null, birthDate=null}, User{id=3, name='null', sex='null', age=null, birthDate=null}, User{id=4, name='null', sex='null', age=null, birthDate=null}, UserDto{id=0, name='null', birthDate=null}, UserDto{id=1, name='null', birthDate=null}, UserDto{id=2, name='null', birthDate=null}, UserDto{id=3, name='null', birthDate=null}, UserDto{id=4, name='null', birthDate=null}]
```

----