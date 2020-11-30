## codec

加解密工具类

### AESUtils

AES算法加解密工具类

- 加解密生成十六进制字符串

``` java
String plainText = "hello";
String key = "123";

// AES默认加密（ECB-256位-PKCS5Padding）
String hexString = AESUtils.encryptAsHexString(plainText, key); // => "5b6d007f0aa27bc3a8e825f4f2f2f869"

// AES默认解密（ECB-256位-PKCS5Padding）
byte[] bytes = AESUtils.decryptHexString(hexString, key); // => hello

```

- 加解密生成Base64字符串

``` java
String plainText = "hello";
String key = "123";

// AES默认加密（ECB-256位-PKCS5Padding）
String base64String = AESUtils.encryptAsBase64String(plainText, key); // => "W20Afwqie8Oo6CX08vL4aQ=="

// AES默认解密（ECB-256位-PKCS5Padding）
byte[] bytes = AESUtils.decryptBase64String(base64String, key); // => hello
```

---

### DESUtils

DES算法加解密工具


