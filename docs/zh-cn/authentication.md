## authentication

### shiro

#### ShiroUtils

- 是否已登录

``` java
ShiroUtils.isLogin();  // => true
```

- 退出登录

``` java
ShiroUtils.logout();  
```

- 获取登录用户信息

``` java
ShiroUtils.getUserInfo();
```

### security

#### SecurityUtils

- 获取当前用户信息

``` java
User user = SecurityUtils.getCurrentUser();
```

- 获取当前用户IP

``` java
String userIp = SecurityUtils.getCurrentUserIp();
```

- 判断用户是否拥有某角色

``` java
SecurityUtils.hasAnyRole("admin", "manager");
```


