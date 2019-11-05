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
RequestUtils.getClientIp();  // => 192.168.1.1
```

### ResponseUtils

响应工具类

- 下载文件

``` java
FileInputStream fis = new FileInputStream(new File("a.xlsx"));
ResponseUtils.download("测试.xlsx", fis); 
```

- 生成html提示消息

``` java
ResponseUtils.buildHtmlMsg("提示", "系统异常"); // => 

ResponseUtils.buildHtmlMsg("提示", "注意", "系统异常"); // => 
```

- 输出html提示消息

``` java

ResponseUtils.printHtmlMsg(response, "提示", "注意", "系统异常");

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