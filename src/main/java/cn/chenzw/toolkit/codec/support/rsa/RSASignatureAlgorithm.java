package cn.chenzw.toolkit.codec.support.rsa;

/**
 * @author chenzw
 */
public enum RSASignatureAlgorithm {

    MD5withRSA("MD5withRSA"),
    SHA1withRSA("SHA1withRSA"),
    SHA256withRSA("SHA256withRSA"),
    SHA384withRSA("SHA384withRSA"),
    SHA512withRSA("SHA512withRSA");

    private String algorithm;

    RSASignatureAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String value() {
        return algorithm;
    }

}
