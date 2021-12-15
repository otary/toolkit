package cn.chenzw.toolkit.codec;

import cn.chenzw.toolkit.codec.support.rsa.RSAKeySize;
import cn.chenzw.toolkit.codec.support.rsa.RSASignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@RunWith(JUnit4.class)
public class RSAUtilsTests {

    @Test
    public void testGetPrivateKey() throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, IOException, SignatureException {

        String content = "Hello World";
        String privateKeyStr = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC4fEAtBCIdy1Mq\n" +
                "REnPVaU00vnlNpkuamrdaBNQ/qjuGo40K36WuEB6xJvOjfd6sjAgG2DWOpBr4dh7\n" +
                "vY/Tntb3rh1McBAQxdvHk4rsdYzpVRiWYoWnN8UbPMBI/dL34gbXY4sgrPfx+qJs\n" +
                "jvFnOC0yuxkzTNaY3bM+S5grfM2nc6sbNYNs8Zh4lZxbrDCnCjyh+F5Ru6mBEqlW\n" +
                "Sk86S41vX8GcIcQHE0IX+gJ5FTqGJhkUkwWHGRsGjcB3P0/T2ZEIkj1iGH+G+K+7\n" +
                "1CGaV7z6JmFzY3/j6/p5BkGIGs7COATL22injSbm3IxjjTYT/h5Y1VJ0z0V5dSNK\n" +
                "zqy27H91AgMBAAECggEAZPwOnRWgHzMHSSGsTYQSNMIv7Fpgq1mxOZUYzcM4cW2z\n" +
                "3bbZSmN9WDZ7Gyv3BgC3+ztBXMMd/w/XUpTHQA24mkeC3i6RfLzxu2N2Lk2CXs8n\n" +
                "8Fnp00jh6jDtCId4jhv5prlklPRZnOmXM1ljpx7HDvf/qWcsKO1iin0SxOtCH6Bx\n" +
                "O3JjLvjDK2+7Ym2y+PGqJrwQnBmgKONpdUkKSApdA/m33V9bNbRqEs2/9wnkpaNX\n" +
                "hdbyvkJ+/CZnVZJiIi7IlQo3ErP7Q5634ihe2hGXNC7MQlhNS6UtcSkbg7GTQlT8\n" +
                "WpofnEHs+CGFQfBDoM2jgr17NRIC/GnApDmkkBCAwQKBgQDhXOScD5ogEmr5B2SS\n" +
                "C1XeETzDVSsNzvi3SeF3d96ZKXd+QO8ySyOjVFhM+7LqpaM0pHoALbpO79xZBURn\n" +
                "bZDPoen6Q8yUyg4fUMC2nEP8PKG6yaZGV5oSY7AYrzkb3IAxhSQywqjk/PsI+g1r\n" +
                "MCZbPVHY61b/r62Ro2BLCx1ciQKBgQDRkLvBSoC3RD/a2MoHWN0Jiw0MEFgslUn0\n" +
                "Yla4YauAaL2MMZI3nK7BNWHV4cov6SG7vU6NOdHoXxmfiPl/OU5YPrCCvLXNbP4B\n" +
                "ohfgv8L96ZczoquhOLl1LPek93HiQMl9RmmcdyujXRUY6sBc6lA3kNLisMCeLDlI\n" +
                "OU4XnWpIjQKBgQCU7VtR2mmi2ikBCqamcIE9MCz0dJrwFB9+mH88AOYEV0fVEjpA\n" +
                "BTG25K05qnew4ub4idzps05UjeQSnA15QAmbfMdvkFBo3yH4BdhklN3EmZjTJCES\n" +
                "iR+TGXesyFa4F75Le03RkZwlPxzx8LTfacFfOCWXcOZcL49HGC0xSlYL6QKBgGqi\n" +
                "vydIBd87d1Kg5Yx/h1oHy4fL+VIWVR9pur7v1BjvMyRXRRPPQo1F4Ja44e0nL+cc\n" +
                "GRB7z3fyjFNFm89FFbyJib+8zrXNNCMYnBXc/he9+RZpO/Q5rQUW8Jyc3PJIDT8V\n" +
                "yT+41Be4kbewHForUM41f1minyDdV/iMUFRr4V0xAoGBAIrj7J3SsOUPE6q/wZ4Y\n" +
                "zBEMvqgINBMb86ouDGwsakZZpVxuSgAj4kSAgD5lsC07Mj7GoVaXdxmrbZz6NcM7\n" +
                "Pg89FfcLKyh7+3ailP+KuUDuRdhdutk4QfVYKzmmtyqB4wNYTk9wff95YGNv7Fxm\n" +
                "J/p+zT/YiiASqGZ9UnxBOYB8";

        String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuHxALQQiHctTKkRJz1Wl\n" +
                "NNL55TaZLmpq3WgTUP6o7hqONCt+lrhAesSbzo33erIwIBtg1jqQa+HYe72P057W\n" +
                "964dTHAQEMXbx5OK7HWM6VUYlmKFpzfFGzzASP3S9+IG12OLIKz38fqibI7xZzgt\n" +
                "MrsZM0zWmN2zPkuYK3zNp3OrGzWDbPGYeJWcW6wwpwo8ofheUbupgRKpVkpPOkuN\n" +
                "b1/BnCHEBxNCF/oCeRU6hiYZFJMFhxkbBo3Adz9P09mRCJI9Yhh/hvivu9Qhmle8\n" +
                "+iZhc2N/4+v6eQZBiBrOwjgEy9top40m5tyMY402E/4eWNVSdM9FeXUjSs6stux/\n" +
                "dQIDAQAB";


        PublicKey publicKey = RSAUtils.parsePublicKey(publicKeyStr);
        PrivateKey privateKey = RSAUtils.parsePrivateKey(privateKeyStr);

        /**
         * Key转Base64
         */
        String publicKeyBase64String = RSAUtils.parseKeyAsBase64String(publicKey);
        Assert.assertEquals(StringUtils.remove(publicKeyStr, "\n"), publicKeyBase64String);

        String privateKeyBase64String = RSAUtils.parseKeyAsBase64String(privateKey);
        Assert.assertEquals(StringUtils.remove(privateKeyStr, "\n"), privateKeyBase64String);


        /**
         * key转PEM格式
         */
        String pemString = RSAUtils.parseAsPEM(publicKey);
        Assert.assertEquals("-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuHxALQQiHctTKkRJz1WlNNL55TaZLmpq3WgTUP6o7hqONCt+lrhAesSbzo33erIwIBtg1jqQa+HYe72P057W964dTHAQEMXbx5OK7HWM6VUYlmKFpzfFGzzASP3S9+IG12OLIKz38fqibI7xZzgtMrsZM0zWmN2zPkuYK3zNp3OrGzWDbPGYeJWcW6wwpwo8ofheUbupgRKpVkpPOkuNb1/BnCHEBxNCF/oCeRU6hiYZFJMFhxkbBo3Adz9P09mRCJI9Yhh/hvivu9Qhmle8+iZhc2N/4+v6eQZBiBrOwjgEy9top40m5tyMY402E/4eWNVSdM9FeXUjSs6stux/dQIDAQAB\n" +
                "-----END PUBLIC KEY-----", pemString);

        /**
         * 公钥加密，私钥解密
         */
        byte[] encryptContent = RSAUtils.encrypt(content.getBytes(), publicKey);
        byte[] decryptContent = RSAUtils.decrypt(encryptContent, privateKey);
        Assert.assertEquals(content, new String(decryptContent));

        /**
         * 私钥加密，公钥解密
         */
        byte[] encryptContent2 = RSAUtils.encrypt(content.getBytes(), privateKey);
        byte[] decryptContent2 = RSAUtils.decrypt(encryptContent2, publicKey);
        Assert.assertEquals(content, new String(decryptContent2));


        /**
         * 私钥签名，公钥验证
         */
        byte[] signedContent = RSAUtils.sign(content.getBytes(), privateKey, RSASignatureAlgorithm.MD5withRSA);
        boolean result = RSAUtils.verify(content.getBytes(), signedContent, publicKey, RSASignatureAlgorithm.MD5withRSA);
        Assert.assertTrue(result);


        KeyPair keyPair = RSAUtils.createKeyPair(RSAKeySize.BIT_1024);
        String publicKeyBase64String2 = RSAUtils.parseKeyAsBase64String(keyPair.getPublic());
        // System.out.println(publicKeyBase64String2);

        String privateKeyBase64String2 = RSAUtils.parseKeyAsBase64String(keyPair.getPrivate());
        //System.out.println(privateKeyBase64String2);
    }


}
