package cn.chenzw.toolkit.commons;

import cn.chenzw.toolkit.commons.enums.FileType;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

/**
 * 文件工具类
 *
 * @author chenzw
 */
public class FileExtUtils {

    private FileExtUtils() {
    }


    /**
     * 获取两个路径的相对路径
     *
     * @param fullPath
     * @param fragmentPath
     * @return
     */
    public static String relativePath(String fullPath, String fragmentPath) {
        if (StringUtils.isEmpty(fragmentPath)) {
            return fullPath;
        }
        if (StringUtils.isEmpty(fullPath)) {
            return "";
        }
        return Paths.get(fragmentPath).relativize(Paths.get(fullPath)).toString();
    }

    /**
     * 生成UUID名称
     *
     * @param file
     * @return
     */
    public static String uuidFileName(File file) {
        return uuidFileName(file.getName());
    }


    public static String uuidFileName(String originalFileName) {
        String fileExtension = FilenameUtils.getExtension(originalFileName);
        return UUID.randomUUID().toString() + "." + fileExtension;
    }


    /**
     * 获取系统临时文件路径
     *
     * @return
     */
    public static String getTempDir() {
        return System.getProperty("java.io.tmpdir");
    }


    public static FileType getFileType(InputStream is) throws IOException {
        // 缓存前N个字节
        byte[] cacheBytes = new byte[20];
        is.read(cacheBytes, 0, cacheBytes.length);

        FileType[] fileTypes = FileType.values();
        for (FileType fileType : fileTypes) {
            byte[] headBytes = new byte[fileType.headBytes()];
            System.arraycopy(cacheBytes, 0, headBytes, 0, fileType.headBytes());
            String hex = BinaryConvertUtils.bytesToHexString(headBytes).toUpperCase();
            if (Objects.equals(hex, fileType.signatureCode())) {
                return fileType;
            }
        }
        return null;
    }


}
