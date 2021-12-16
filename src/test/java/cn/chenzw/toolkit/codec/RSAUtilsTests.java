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
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
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


        String pemPublicKey = "-----BEGIN PUBLIC KEY-----\n" +
                "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAqFkC1Zg6RBGJbGXxBaSm8sRT/A6A3iHtK8PkWJCOkgqeUUjTMgyLnw+nFsoDVVUmyTdSLB4o7eCongplpclLjm5eKpg0stYsBVi+q5xGkSUIuVr0thBhSm0L77BbmihSBtpT7qm4p5g0I0ARbzQcA0ugHumUajaMz1oF5j4hUktnbRkaSQyW267ER5X2VfqbXBdpBjTBc4OnkHH0ZDG69wPKRGbFaA2ZD9LC1qSIlmYNDwAiKpEHnKHd7CjF5TmAC++mN7sNzwrM0uWDJOHwMedMiZclqjir+qlA5tX6EgDgFoYxaUXbFrD1SH6EEXQ2Kyq3DMbFm6OKVWUlmsd/MG39nxvrNk3E5V25lTGkRUtD2OzddDeaVajt7/bWkQDosruZFLUYluo/IFcQvcwCtIxTxfIP3sc/1WumUBKSYOHM4rqcPV3IkLEBx+sDi0nB0JN/e2ODAX+/fSzfxZsPFLFOKucoPkyYuUgzBW8dNf6O/pceFUsrCSTlOXNjjLDVpSDiA9n237VT06aefBMdTSsq5AGBc1XHurg8Xx3yNrPXowCvH+EmmV4j7tuxkbXXHkNOYLskulGMW/YDKzXYAQ6h1UUI9APR65SUJjDWv27Ch9H2ZmoCAmTf/+RmEtV00D47+JBFtZttu6IVLSvX/62gRSXAmqsxrX6zOxLbvYECAwEAAQ==\n" +
                "-----END PUBLIC KEY-----";

        String pemPrivateKey = "-----BEGIN PRIVATE KEY-----\n" +
                "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\n" +
                "-----END PRIVATE KEY-----";

        PublicKey publicKey2 = RSAUtils.parsePEMAsPublicKey(pemPublicKey);
        PrivateKey privateKey2 = RSAUtils.parsePEMAsPrivateKey(pemPrivateKey);

    }


}
