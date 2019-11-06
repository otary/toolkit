## io


### IOExtUtils

- 拷贝输入流（最大支持2G大小）

``` java
InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("io/test.txt");

// 拷贝输入流
InputStream copyIs = IOExtUtils.copy(is);

is.available(); // => 19
copyIs.available(); // => 19

```