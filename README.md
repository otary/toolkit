# toolkit

Java扩展工具库

## commons

### StringExtUtils

- **驼峰转下划线**
```
StringExtUtils.toUnderscore("HelloWorld");  // => "hello_world"
StringExtUtils.toUnderscore("helloWorld");   // =>  "hello_world"
StringExtUtils.toUnderscore("Hello2World");  // =>  "hello2_world"
StringExtUtils.toUnderscore("HElloWOrld");   // => "h_ello_w_orld"
```

- **下划线转驼峰**
```
StringExtUtils.toCamel("hello_world");  // => "helloWorld"
StringExtUtils.toCamel("HELLO_WORLD");  // => "helloWorld"
StringExtUtils.toCamel("Hello_WoRld");  // => "helloWorld"
```

- **分隔大写字符**
```
StringExtUtils.uppercaseSeparate("HelloWorld", "-");  // => "hello-world"
StringExtUtils.uppercaseSeparate("helloWorld", "@");  // => "hello@world"
```

- 获取第一个分离点之后的文本

```
StringExtUtils.subStringFirstAfter("abcdef", "c");  // => def
StringExtUtils.subStringFirstAfter("abcdef", "g");  // => abcdef
StringExtUtils.subStringFirstAfter("abcdefabcdef", "c");  // => defabcdef
```

### RandomStringExtUtils

- **随机生成中文字符**
```
RandomStringExtUtils.randomChinese(5); // => 已很结同总
```

- **随机生成常用中文字符（包括复杂字符）**
```
// 随机生成3到5个中文字符
RandomStringExtUtils.randomFrequentlyUsedChinese(3,5); // => 耆牮俤畸蹬
```

- **随机生成姓名**
```
RandomStringExtUtils.randomName();  // => 虞任
```

- **列表中随机取值**
```
String name = RandomStringExtUtils.randomFromList("张三", "李四", "王五", "赵六");  
int i = RandomStringExtUtils.randomFromList(1,7,9,10);  // => 

```

### DateExtUtils

- **随机生成日期**

```
 Date randomDate = DateExtUtils.random();
```

- **随机生成指定范围内的日期**

```
Calendar startDate = Calendar.getInstance();
startDate.set(2009, 10, 10);

Calendar endDate = Calendar.getInstance();
endDate.set(2019, 10, 10);
        
Date randomDate = DateExtUtils.random(startDate.getTime(), endDate.getTime());
```
- 获取指定月份的第一天

```
Calendar calendar = Calendar.getInstance();
calendar.set(2019, 2, 1, 0, 0, 0); // 月份值要减1
calendar.set(Calendar.MILLISECOND, 0);

Date firstDayOfMonth = DateExtUtils.getFirstDayOfMonth("2019-03", "yyyy-MM");
Assert.assertEquals(calendar.getTime().getTime(), firstDayOfMonth.getTime());
```

- 获取指定月份的最后一天

```
Calendar calendar = Calendar.getInstance();
calendar.set(2019, 2, 31, 0, 0, 0); // 月份值要减1
calendar.set(Calendar.MILLISECOND, 0);

Date lastDayOfMonth = DateExtUtils.getLastDayOfMonth("2019-03", "yyyy-MM");
Assert.assertEquals(calendar.getTime().getTime(), lastDayOfMonth.getTime());
```

- 判断指定日期是否在某个区间范围内

```
Calendar calendar = Calendar.getInstance();
calendar.set(2019, 3, 20);

Calendar startDateCalendar = Calendar.getInstance();
startDateCalendar.set(2019, 3, 1);

Calendar endDateCalendar = Calendar.getInstance();
endDateCalendar.set(2019, 3, 31);
boolean dayBetween = DateExtUtils
                .isDayBetween(calendar.getTime(), startDateCalendar.getTime(), endDateCalendar.getTime());
```

### ListExtUtils

- 提取列表对象中某个字段的值，并拼接成字符串

```
List<User> users = new ArrayList<>();
for (int i = 0; i < 10; i++) {
    User user = new User();
    user.setId(i);
    user.setName("zhangsan" + i);
    users.add(user);
}
String ids = ListExtUtils.joinFieldValue(users, "id", "#");  // => 0#1#2#3#4#5#6#7#8#9
String ids2 = ListExtUtils.joinFieldValue(users, "id");  // => 0,1,2,3,4,5,6,7,8,9
```

### SerializationExtUtils

- 序列化对象（Object -> 十六进制字符串）

```
User user = new User();
user.setName("张三");
user.setId(1);
user.setBirthDate(new Date(2019, 10, 10));
user.setAge(20);

// 序列化
String serializedHexString = SerializationExtUtils.serialize(user);  // => aced000573720024636e2e6368656e7a772e746f6f6c6b69742e646f6d61696e2e656e746974792e55736572983a612a7abc9f590200054c00036167657400134c6a6176612f6c616e672f496e74656765723b4c00096269727468446174657400104c6a6176612f7574696c2f446174653b4c0002696471007e00014c00046e616d657400124c6a6176612f6c616e672f537472696e673b4c000373657871007e00037870737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870000000147372000e6a6176612e7574696c2e44617465686a81014b59741903000078707708000037f668c4a400787371007e000500000001740006e5bca0e4b88970

```

- 反序列化对象（十六进制字符串 -> Object）

```
User deserializeUser = SerializationExtUtils.deserialize(serializedHexString); // => User{id=1, name='张三', sex='null', age=20, birthDate=Mon Nov 10 00:00:00 CST 3919}

```

### ColorUtils

- 将十六进制颜色转RGB格式

```
Color color = ColorUtils.hexToRgb("70AD47");
// Color color = ColorUtils.hexToRgb("#70AD47");

Assert.assertEquals(color.getRed(), 112);
Assert.assertEquals(color.getGreen(), 173);
Assert.assertEquals(color.getBlue(), 71);
```

### RegexUtils

正则匹配工具类

- **是否IP地址**
  - 是否IPv4
  - 是否IPv6

```
Assert.assertTrue(RegexUtils.isIPv4("10.2.2.4"));
Assert.assertTrue(RegexUtils.isIPv4("192.168.255.255"));

Assert.assertFalse(RegexUtils.isIPv4("1.1.1.1/12"));
Assert.assertFalse(RegexUtils.isIPv4("1.1.1"));
```

- **是否邮箱地址**

```
Assert.assertTrue(RegexUtils.isEmail("656469722@qq.com"));
Assert.assertTrue(RegexUtils.isEmail("chenzw_123@16.com"));

// 非法字符串"#@"
Assert.assertFalse(RegexUtils.isEmail("chenzw#123@163.com"));
Assert.assertFalse(RegexUtils.isEmail("chenzw@123@163.com"));
```
- **是否QQ号码**
```

```

- **是否身份证号码**
```

```

- **是否手机号码**
```

```

- **中文字符匹配**
  - 是否包含中文
  - 是否全是中文
```

```

- **数值**
  - 是否整数
  - 是否数字（整数和小数）
```

```

### ClassExtUtils

- **判断某个类是否存在**

```
 boolean present = ClassExtUtils.isPresent("cn.chenzw.toolkit.commons.DateExtUtils");  // => true
```

- **实例化对象**

```
 Class<?> aClass = ClassExtUtils.forName("cn.chenzw.toolkit.commons.DateExtUtils"); // => Class对象
```

- **查找类所在Jar包**

```
 URL sourceJar = ClassExtUtils.findSourceJar(DateUtils.class);  // => file:/C:/Users/yunli/.m2/repository/org/apache/commons/commons-lang3/3.9/commons-lang3-3.9.jar
```

### GenericUtils

泛型类工具

- 获取泛型参数

```

```

### BinaryConvertUtils

- **bytes数组<=>十六进制字符串**

```
BinaryConvertUtils.bytesToHexString("hello".getBytes());  // => 68656c6c6f
byte[] bytes = BinaryConvertUtils.hexStringToBytes("68656c6c6f"); // => hello
```

### UriExtUtils

uri操作工具类

- 添加参数

```
Map<String, String> params = new HashMap<>();
params.put("a", "111");
params.put("b", "222");

String uri = UriExtUtils.buildParams("http://www.baidu.com", params);  // => http://www.baidu.com?a=111&b=222

String uri2 = UriExtUtils.buildParams("http://www.baidu.com?k=1", params);  // => http://www.baidu.com?k=1&a=111&b=222

```

- 解析并获取uri参数

```
Map<String, String> uriParams = UriExtUtils.getUriParams(
                "http://192.168.17.231:8680/login?client_id=1&redirect_uri=http%3A%2F%2Fwww.baidu.com&authentication_type=oauth");  // => {redirect_uri=http%3A%2F%2Fwww.baidu.com, authentication_type=oauth, client_id=1}

```

---

## dozer

### DozerUtils

- **拷贝对象属性**
```
 // 将List<User>转换成List<UserDto>
List<UserDto> userDtos = DozerUtils.mapList(mapper, users, UserDto.class);
```

- 支持自定义字段转换

```
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

```
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
---
## codec

### AESUtils

AES加解密工具

- **加解密生成十六进制字符串**

```
String plainText = "hello";
String key = "123";

// AES默认加密（ECB-256位-PKCS5Padding）
String hexString = AESUtils.encryptAsHexString(plainText, key); // => "5b6d007f0aa27bc3a8e825f4f2f2f869"

// AES默认解密（ECB-256位-PKCS5Padding）
byte[] bytes = AESUtils.decryptHexString(hexString, key); // => hello

```

- **加解密生成Base64字符串**
```
String plainText = "hello";
String key = "123";

// AES默认加密（ECB-256位-PKCS5Padding）
String base64String = AESUtils.encryptAsBase64String(plainText, key); // => "W20Afwqie8Oo6CX08vL4aQ=="

// AES默认解密（ECB-256位-PKCS5Padding）
byte[] bytes = AESUtils.decryptBase64String(base64String, key); // => hello
```
---

## http

### HttpRequestWrapper

HttpRequest包装类

```
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

```
// Spring环境
HttpServletRequest httpRequest = HttpHolder.getRequest();  // 获取HttpServletRequest
HttpServletResponse httpResponse = HttpHolder.getResponse();  // 获取HttpServletResponse
```

### ContentCachingRequestWrapperFilter

缓存HTTP请求内容（可多次获取HTTP请求内容）

```
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

```
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

#### EhCacheUtils

EhCache2.x工具类

```

```
---

## logging

### Log4j2Utils

log4j2工具类

```

```

### LogbackUtils

logback工具类

```

```

---

## spring

### JoinPointWrapper

Spring切面包装类

```

```

### ResourceScannerUtils

- 扫描指定后缀的资源文件
```
// 扫描xml文件
Set<Resource> xmlResources = ResourceScannerUtils.scan("cn.chenzw.toolkit", ResourceScannerUtils.SUFFIX.XML);

// 扫描所有文件
Set<Resource> allResources = ResourceScannerUtils.scan("cn.chenzw.toolkit", ResourceScannerUtils.SUFFIX.ALL);
```

- 扫描包下的所有类

```
Set<Class<?>> classes = ResourceScannerUtils.scanClass("cn.chenzw.toolkit");
Assert.assertTrue(classes.contains(ResourceScannerUtils.class));
Assert.assertTrue(classes.contains(JoinPointWrapper.class));
```

- 扫描指定超类及其后裔的类

```
// 扫描Writeable接口的实现类/继承类
Set<Class<?>> superClasses = ResourceScannerUtils.scanClassFromSuper("cn.chenzw.toolkit", new Class[]{Writeable.class});
Assert.assertTrue(superClasses.contains(Writeable.class));
Assert.assertTrue(superClasses.contains(BookDto.class));
```

- 扫描指定注解标注的类

```
// 扫描@SSO注解的类
Set<Class<?>> annoClasses = ResourceScannerUtils.scanClassFromAnnotation("cn.chenzw.toolkit", new Class[]{SSO.class});
Assert.assertTrue(annoClasses.contains(BookDto.class));       
```

### SpringUtils

- 注册Bean

```
// 注册bean
User userBean = SpringUtils.registerBean(User.class);

// 指定bean名称注册
Book bookBean = SpringUtils.registerBean("book2", Book.class);
```

- 获取Bean

```
// 获取指定bean名称的bean
Object user = SpringUtils.getBean("cn.chenzw.toolkit.domain.entity.User#0");

// 获取指定类的bean
User userBean3 = SpringUtils.getBean(User.class);

Object book = SpringUtils.getBean("book2");

Book bookBean2 = SpringUtils.getBean(Book.class);
```

- 获取所有的Bean（详细信息）

```
ContextBeans beans = SpringUtils.getBeans();  // => ContextBeans{beans=[BeanDescriptor{name='appConfig', aliases=[], scope='singleton', type=class cn.chenzw.toolkit.spring.config.AppConfig$$EnhancerBySpringCGLIB$$6d190be5, resource='null', dependencies=[]}, BeanDescriptor{name='springUtils', aliases=[], scope='singleton', type=class cn.chenzw.toolkit.spring.util.SpringUtils, resource='cn.chenzw.toolkit.spring.config.AppConfig', dependencies=[]}], parentId='null'}

```

---
## 运行单元测试

$ mvn test
