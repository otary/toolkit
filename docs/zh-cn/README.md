# toolkit

Java扩展工具库

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3a01d0ffcca4412594bd3d80c5cdf90f)](https://www.codacy.com/manual/otary/toolkit?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=otary/toolkit&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/otary/toolkit.svg?branch=master)](https://travis-ci.org/otary/toolkit)
[![codecov](https://codecov.io/gh/otary/toolkit/branch/master/graph/badge.svg)](https://codecov.io/gh/otary/toolkit)


[](commons.md ':include')

[](dozer.md ':include')

[](freemarker.md ':include')

[](codec.md ':include')

[](http.md ':include')

[](cache.md ':include')

[](logging.md ':include')

[](spring.md ':include')

[](datasource.md ':include')


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

## 运行单元测试

$ mvn test


