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