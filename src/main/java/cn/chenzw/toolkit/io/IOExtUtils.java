package cn.chenzw.toolkit.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

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
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) > -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        is.reset();
        return new ByteArrayInputStream(baos.toByteArray());
    }
}
