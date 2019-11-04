# toolkit

Java扩展工具库

## http

### HttpRequestWrapper

HttpRequest包装类

``` java
// Servlet环境
HttpRequestWrapper httpRequestWrapper = new HttpRequestWrapper(request);

// Spring环境
HttpRequestWrapper httpRequestWrapper = new HttpRequestWrapper();

httpRequestWrapper.getMethod();  // HTTP方法
httpRequestWrapper.getURI();   // URI链接
httpRequestWrapper.getQueryString();  // 请求参数
httpRequestWrapper.getClientIp();  // 请求的客户端IP
httpRequestWrapper.getThreadId();   // 请求线程ID
httpRequestWrapper.getThreadName();  // 请求线程名称
httpRequestWrapper.getBodyString();  // HTTP Body内容
httpRequestWrapper.isMultipart();  // 是否上传文件
```

### HttpHolder

``` java
// Spring环境
HttpServletRequest httpRequest = HttpHolder.getRequest();  // 获取HttpServletRequest
HttpServletResponse httpResponse = HttpHolder.getResponse();  // 获取HttpServletResponse
```

### RequestUtils

请求工具类

- 获取请求参数的第一个参数

``` java
RequestUtils.getFirstParamter(request.getParameter("aaa"));
```

- 获取客户端IP

``` java
RequestUtils.getClientIp();
```

### ResponseUtils

响应工具类

- 下载文件

``` java

```

- 生成html提示消息

``` java

```

- 输出html提示消息

``` java

```

### ContentCachingRequestWrapperFilter

缓存HTTP请求内容（可多次获取HTTP请求内容）

``` java
@Bean
public FilterRegistrationBean contentCachingRequestWrapperFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setDispatcherTypes(DispatcherType.REQUEST);
    registration.setFilter(new ContentCachingRequestWrapperFilter());
    registration.addUrlPatterns("/*");
    registration.setName("contentCachingRequestWrapperFilter");
    registration.setOrder(Integer.MAX_VALUE);
    return registration;
}

```

### XssFilter

Xss过滤器

``` java
@Bean
public FilterRegistrationBean xssFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setDispatcherTypes(DispatcherType.REQUEST);
    registration.setFilter(new XssFilter("/sys/"));
    registration.addUrlPatterns("/*");
    registration.setName("xssFilter");
    registration.setOrder(Integer.MAX_VALUE);
    return registration;
}

```

---
## cache

### EhCacheUtils

EhCache2.x工具类

``` java

```
---

## logging

### Log4j2Utils

log4j2工具类

``` java

```

### LogbackUtils

logback工具类

``` java

```

---

## spring

### JoinPointWrapper

Spring切面包装类

``` java

```

### ResourceScannerUtils

- 扫描指定后缀的资源文件

``` java
// 扫描xml文件
Set<Resource> xmlResources = ResourceScannerUtils.scan("cn.chenzw.toolkit", ResourceScannerUtils.SUFFIX.XML);

// 扫描所有文件
Set<Resource> allResources = ResourceScannerUtils.scan("cn.chenzw.toolkit", ResourceScannerUtils.SUFFIX.ALL);
```

- 扫描包下的所有类

``` java
Set<Class<?>> classes = ResourceScannerUtils.scanClass("cn.chenzw.toolkit");
Assert.assertTrue(classes.contains(ResourceScannerUtils.class));
Assert.assertTrue(classes.contains(JoinPointWrapper.class));
```

- 扫描指定超类及其后裔的类

``` java
// 扫描Writeable接口的实现类/继承类
Set<Class<?>> superClasses = ResourceScannerUtils.scanClassFromSuper("cn.chenzw.toolkit", new Class[]{Writeable.class});
Assert.assertTrue(superClasses.contains(Writeable.class));
Assert.assertTrue(superClasses.contains(BookDto.class));
```

- 扫描指定注解标注的类

``` java
// 扫描@SSO注解的类
Set<Class<?>> annoClasses = ResourceScannerUtils.scanClassFromAnnotation("cn.chenzw.toolkit", new Class[]{SSO.class});
Assert.assertTrue(annoClasses.contains(BookDto.class));       
```

### SpringUtils

- 注册Bean

``` java
// 注册bean
User userBean = SpringUtils.registerBean(User.class);

// 指定bean名称注册
Book bookBean = SpringUtils.registerBean("book2", Book.class);
```

- 注册controller

``` java
RequestMappingInfo requestMappingInfo = RequestMappingInfo.paths("/staff/list").methods(RequestMethod.GET).build();
Staff staff = SpringUtils.getBean("staff");
Method getUserNameMethod = staff.getClass().getDeclaredMethod("getUserName",String.class);
SpringUtils.registerController(requestMappingInfo, staff, getUserNameMethod);

```

- 获取Bean

``` java
// 获取指定bean名称的bean
Object user = SpringUtils.getBean("cn.chenzw.toolkit.domain.entity.User#0");

// 获取指定类的bean
User userBean3 = SpringUtils.getBean(User.class);

Object book = SpringUtils.getBean("book2");

Book bookBean2 = SpringUtils.getBean(Book.class);
```

- 获取所有的Bean（详细信息）

``` java
ContextBeans beans = SpringUtils.getBeans();  // => ContextBeans{beans=[BeanDescriptor{name='appConfig', aliases=[], scope='singleton', type=class cn.chenzw.toolkit.spring.config.AppConfig$$EnhancerBySpringCGLIB$$6d190be5, resource='null', dependencies=[]}, BeanDescriptor{name='springUtils', aliases=[], scope='singleton', type=class cn.chenzw.toolkit.spring.util.SpringUtils, resource='cn.chenzw.toolkit.spring.config.AppConfig', dependencies=[]}], parentId='null'}

```

---

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


