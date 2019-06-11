# toolkit

Java扩展工具库


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

```

```

---
## 运行单元测试

$ mvn test
