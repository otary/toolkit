## freemarker

### FreeMarkerUtils

FreeMarker工具类

- 将模版文件渲染成字符串输出

``` java
List<User> users = new ArrayList<>();
Map<String, Object> userMap = new HashMap<>();
userMap.put("users", users);

URL templateFile = Thread.currentThread().getContextClassLoader().getResource("freemarker/template.ftl");
String result = FreeMarkerUtils.processToString(new File(templateFile.toURI()), userMap);

```

- 将模版文件渲染生成文件

``` java
List<User> users = new ArrayList<>();
URL templateFile = Thread.currentThread().getContextClassLoader().getResource("freemarker/template.ftl");
File outFile = new File("target/result.txt");
FreeMarkerUtils.processToFile(new File(templateFile.toURI()), userMap, outFile);
```

- 将字符串模版渲染生成字符串输出

``` java
List<User> users = new ArrayList<>();
String result = FreeMarkerUtils.processToString("用户:${user.name}", userMap); 
```

---