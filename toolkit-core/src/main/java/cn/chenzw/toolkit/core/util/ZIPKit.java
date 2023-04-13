package cn.chenzw.toolkit.core.util;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public final class ZIPKit {

    private ZIPKit() {

    }


    /**
     * 压缩文件
     *
     * @param srcDirectory
     * @param outputStream
     */
    public static void toZip(File srcDirectory, OutputStream outputStream) {
        long t1 = System.currentTimeMillis();
        try (ZipOutputStream zos = new ZipOutputStream(outputStream)) {
            doCompress(srcDirectory, zos, srcDirectory.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        long t2 = System.currentTimeMillis();
        log.debug("Finish zip [{}], cost {} ms", srcDirectory.getPath(), (t2 - t1));
    }

    /**
     * 压缩文件（多个文件）
     *
     * @param srcFiles
     * @param outputStream
     */
    public static void toZip(List<File> srcFiles, OutputStream outputStream) {
        try (ZipOutputStream zos = new ZipOutputStream(outputStream)) {
            for (File srcFile : srcFiles) {
                byte[] buff = new byte[1024];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));

                int len;
                try (FileInputStream in = new FileInputStream(srcFile)) {
                    while ((len = in.read(buff)) != -1) {
                        zos.write(buff, 0, len);
                    }
                    zos.closeEntry();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doCompress(File srcFile, ZipOutputStream zos, String name) throws IOException {
        byte[] buff = new byte[1024];
        if (srcFile.isFile()) {
            zos.putNextEntry(new ZipEntry(name));

            try (FileInputStream fis = new FileInputStream(srcFile)) {
                int len;
                while ((len = fis.read(buff)) != -1) {
                    zos.write(buff, 0, len);
                }
                zos.closeEntry();
            }
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
