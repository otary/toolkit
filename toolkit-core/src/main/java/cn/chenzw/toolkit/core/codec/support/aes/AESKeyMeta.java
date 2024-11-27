package cn.chenzw.toolkit.core.codec.support.aes;

import org.springframework.util.StringUtils;

/**
 * @author chenzw
 */
public enum AESKeyMeta {

    BIT_128(128), BIT_192(192), BIT_256(256);

    private int bitLen;

    AESKeyMeta(int value) {
        this.bitLen = value;
    }

    public int bitLen() {
        return bitLen;
    }

    public static void main(String[] args) {
        String a = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsic2FudGFyaXplbCIsInBocyIsImh0dHBzOi8vc3RvcmFnZS5jb20vbWV0YS5qc29uIiwiZXhwIjoxNTI1ODkxOTUxfQ.A9gT5xp_079pEf2j3LvWP_qO1mh58-j-ZsZr4T3cOPE";
        if (StringUtils.startsWithIgnoreCase(a, "Bearer")) {
            a = StringUtils.replace(a, "Bearer", "");
        }
        System.out.println(a);
    }
}
