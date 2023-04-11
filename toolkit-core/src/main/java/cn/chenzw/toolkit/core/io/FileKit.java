package cn.chenzw.toolkit.core.io;

import cn.chenzw.toolkit.core.enums.FileType;
import cn.chenzw.toolkit.core.lang.RadixKit;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
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
public class FileKit {

    private FileKit() {
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
     * 根据文件生成UUID名称
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

        String cacheHeadBytesHex = RadixKit.bytesToHexString(cacheBytes).toUpperCase();
        log.debug("File HeadBytes hex is [{}]", cacheHeadBytesHex);

        FileType[] fileTypes = FileType.values();
        for (FileType fileType : fileTypes) {
            byte[] headBytes = new byte[fileType.headBytes()];
            System.arraycopy(cacheBytes, 0, headBytes, 0, fileType.headBytes());
            String hex = RadixKit.bytesToHexString(headBytes).toUpperCase();

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

        String cacheHeadBytesHex = RadixKit.bytesToHexString(cacheBytes).toUpperCase();
        log.debug("File HeadBytes hex is [{}]", cacheHeadBytesHex);

        List<FileType> matchedFileTypes = new ArrayList<>();
        FileType[] fileTypes = FileType.values();
        for (FileType fileType : fileTypes) {
            byte[] headBytes = new byte[fileType.headBytes()];
            System.arraycopy(cacheBytes, 0, headBytes, 0, fileType.headBytes());
            String hex = RadixKit.bytesToHexString(headBytes).toUpperCase();

            if (Objects.equals(hex, fileType.signatureCode())) {
                matchedFileTypes.add(fileType);
            }
        }

        if (matchedFileTypes.isEmpty()) {
            log.debug("File HeadBytes hex [{}] not matched!", cacheHeadBytesHex);
        }

        return matchedFileTypes.toArray(new FileType[matchedFileTypes.size()]);
    }

    /**
     * 文件本地下载
     *
     * @param url      文件链接
     * @param savePath 保存地址
     * @return
     * @throws IOException
     */
    public static File download(String url, File savePath) throws IOException {
        OkHttpClient httpClient = new OkHttpClient.Builder().build();
        try (Response response = httpClient.newCall(new Request.Builder().url(url).build()).execute()) {
            InputStream is = response.body().byteStream();
            String name = FilenameUtils.getName(url);

            File file = new File(savePath.getPath(), name);
            if (!savePath.exists()) {
                savePath.mkdirs();
            }
            IOUtils.copy(is, new FileOutputStream(file));
            return file;
        }
    }

}
