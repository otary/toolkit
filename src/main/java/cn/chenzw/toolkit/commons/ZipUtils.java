package cn.chenzw.toolkit.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩工具
 *
 * @author chenzw
 */
public class ZipUtils {

    private static final Logger logger = LoggerFactory.getLogger(ZipUtils.class);

    private ZipUtils() {
    }


    /**
     * 压缩文件
     *
     * @param srcDirectory
     * @param outputStream
     */
    public static void toZip(File srcDirectory, OutputStream outputStream) {
        long t1 = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(outputStream);
            doCompress(srcDirectory, zos, srcDirectory.getName());

            long t2 = System.currentTimeMillis();
            logger.debug("Finish zip [{}], cost {} ms", srcDirectory.getPath(), (t2 - t1));
        } catch (Exception e) {
            throw new RuntimeException("Zip [" + srcDirectory.getPath() + "] fail!", e);
        } finally {
            try {
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void toZip(List<File> srcFiles, OutputStream outputStream) {
        ZipOutputStream zos = null;

        try {
            zos = new ZipOutputStream(outputStream);
            for (File srcFile : srcFiles) {
                byte[] buff = new byte[1024];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));

                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buff)) != -1) {
                    zos.write(buff, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Zip [" + srcFiles + "] fail!", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void doCompress(File srcFile, ZipOutputStream zos, String name) throws IOException {
        byte[] buff = new byte[1024];
        if (srcFile.isFile()) {
            zos.putNextEntry(new ZipEntry(name));

            FileInputStream fis = new FileInputStream(srcFile);
            int len;
            while ((len = fis.read(buff)) != -1) {
                zos.write(buff, 0, len);
            }
            zos.closeEntry();
            fis.close();
        } else {
            File[] files = srcFile.listFiles();
            // 空文件夹处理
            if (files == null || files.length == 0) {
                zos.putNextEntry(new ZipEntry(name + File.separator));
                zos.closeEntry();
            } else {
                for (File file : files) {
                    doCompress(file, zos, name + File.separator + file.getName());
                }
            }
        }
    }

}
