# toolkit

Java扩展工具库

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

---
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

---
### RegexUtils

正则匹配工具类

- **是否IP地址**
  - 是否IPv4
  - 是否IPv6
```

```

- **是否邮箱地址**
```

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

---

### DozerUtils

- **拷贝对象属性**
```
 // 将List<User>转换成List<UserDto>
List<UserDto> userDtos = DozerUtils.mapList(mapper, users, UserDto.class);
```


### ClassExtUtils

- **判断某个类是否存在**
```

```

### BinaryConvertUtils

- **bytes数组<=>十六进制字符串**
```
BinaryConvertUtils.bytesToHexString("hello".getBytes());  // => 68656c6c6f
byte[] bytes = BinaryConvertUtils.hexStringToBytes("68656c6c6f"); // => hello
```

### JoinPointWrapper



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
## 运行单元测试

$ mvn test
