package cn.chenzw.toolkit.core.codec;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.security.*;

/**
 * @author chenzw
 */
public class KeyPairKit {

    /**
     * 读取KeyPair
     *
     * @param r
     * @return
     * @throws IOException
     */
    public static KeyPair readKeyPair(Reader r) throws IOException {
        try (PEMParser parser = new PEMParser(r)) {
            PEMKeyPair keyPair = (PEMKeyPair) parser.readObject();
            return new JcaPEMKeyConverter().getKeyPair(keyPair);
        } catch (PEMException ex) {
            throw new IOException("Invalid PEM file", ex);
        }
    }

    /**
     * 生成 RSA KeyPair
     *
     * @param keySize
     * @return
     */
    public static KeyPair createKeyPair(int keySize) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(keySize);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * 生成 EC KeyPair
     *
     * @param name
     * @return
     */
    public static KeyPair createECKeyPair(String name) {
        try {
            ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec(name);
            KeyPairGenerator g = KeyPairGenerator.getInstance("ECDSA", "BC");
            g.initialize(ecSpec, new SecureRandom());
            return g.generateKeyPair();
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException ex) {
            throw new IllegalArgumentException("Invalid curve name " + name, ex);
        } catch (NoSuchProviderException ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * 生成PEM字符串
     *
     * @param keyPair
     * @return
     * @throws IOException
     */
    public static String buildPEMString(KeyPair keyPair) throws IOException {
        try (StringWriter sw = new StringWriter()) {
            try (JcaPEMWriter jw = new JcaPEMWriter(sw)) {
                jw.writeObject(keyPair);
            }
            return sw.toString();
        }
    }
}
