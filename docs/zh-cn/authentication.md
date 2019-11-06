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