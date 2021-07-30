package cn.chenzw.toolkit.io;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Objects;

/**
 * @author chenzw
 */
public final class IOExtUtils {

    /**
     * 拷贝输入流（最大支持2G大小文件）
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static InputStream copy(InputStream is) throws IOException {
        Objects.requireNonNull(is, "inputStream must not be null!");

        if (!is.markSupported()) {
            throw new IllegalArgumentException(is.getClass().getName() + " do not support mark, you can wrapper java.io.BufferedInputStream");
        }

        is.mark(Integer.MAX_VALUE);
        ByteArrayOutputStream baos = toByteArrayOutputStream(is);
        is.reset();
        return new ByteArrayInputStream(baos.toByteArray());
    }

    /**
     * 将输出流转输入流
     *
     * @param os
     * @param is
     * @since 1.0.3
     */
    public static void copy(OutputStream os, InputStream is) {
        if (os instanceof ByteArrayOutputStream) {
            ByteArrayOutputStream baos = (ByteArrayOutputStream) os;
            is = new ByteArrayInputStream(baos.toByteArray());
        } else {
            throw new IllegalArgumentException("Just support ByteArrayOutputStream!");
        }
    }

    /**
     * 转ByteArrayOutputStream
     * @param is
     * @return
     * @throws IOException
     */
    public static ByteArrayOutputStream toByteArrayOutputStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) > -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        return baos;
    }
}
