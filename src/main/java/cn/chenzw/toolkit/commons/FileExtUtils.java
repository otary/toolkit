package cn.chenzw.toolkit.commons;

import cn.chenzw.toolkit.commons.enums.FileType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * 文件工具类
 *
 * @author chenzw
 */
@Slf4j
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


    /**
     * 获取文件类型
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static FileType getFileType(InputStream is) throws IOException {
        // 缓存前N个字节
        byte[] cacheBytes = new byte[20];
        is.read(cacheBytes, 0, cacheBytes.length);

        String cacheHeadBytesHex = BinaryConvertUtils.bytesToHexString(cacheBytes).toUpperCase();
        log.debug("File HeadBytes hex is [{}]", cacheHeadBytesHex);

        FileType[] fileTypes = FileType.values();
        for (FileType fileType : fileTypes) {
            byte[] headBytes = new byte[fileType.headBytes()];
            System.arraycopy(cacheBytes, 0, headBytes, 0, fileType.headBytes());
            String hex = BinaryConvertUtils.bytesToHexString(headBytes).toUpperCase();

            if (Objects.equals(hex, fileType.signatureCode())) {
                return fileType;
            }
        }

        log.debug("File HeadBytes hex [{}] not matched!", cacheHeadBytesHex);

        return null;
    }

    /**
     * 获取可能的文件类型列表
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static FileType[] getProbableFileType(InputStream is) throws IOException {
        // 缓存前N个字节
        byte[] cacheBytes = new byte[20];
        is.read(cacheBytes, 0, cacheBytes.length);

        String cacheHeadBytesHex = BinaryConvertUtils.bytesToHexString(cacheBytes).toUpperCase();
        log.debug("File HeadBytes hex is [{}]", cacheHeadBytesHex);

        List<FileType> matchedFileTypes = new ArrayList<>();
        FileType[] fileTypes = FileType.values();
        for (FileType fileType : fileTypes) {
            byte[] headBytes = new byte[fileType.headBytes()];
            System.arraycopy(cacheBytes, 0, headBytes, 0, fileType.headBytes());
            String hex = BinaryConvertUtils.bytesToHexString(headBytes).toUpperCase();

            if (Objects.equals(hex, fileType.signatureCode())) {
                matchedFileTypes.add(fileType);
            }
        }

        if (matchedFileTypes.isEmpty()) {
            log.debug("File HeadBytes hex [{}] not matched!", cacheHeadBytesHex);
        }

        return matchedFileTypes.toArray(new FileType[matchedFileTypes.size()]);
    }


}
