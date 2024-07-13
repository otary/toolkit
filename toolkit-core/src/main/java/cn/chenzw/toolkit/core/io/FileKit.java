package cn.chenzw.toolkit.core.io;

import cn.chenzw.toolkit.core.enums.FileType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        return UUID.randomUUID() + "." + fileExtension;
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

        String cacheHeadBytesHex = Hex.encodeHexString(cacheBytes).toUpperCase();
        log.debug("File HeadBytes hex is [{}]", cacheHeadBytesHex);

        FileType[] fileTypes = FileType.values();
        for (FileType fileType : fileTypes) {
            byte[] headBytes = new byte[fileType.headBytes()];
            System.arraycopy(cacheBytes, 0, headBytes, 0, fileType.headBytes());
            String hex = Hex.encodeHexString(headBytes).toUpperCase();

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

        String cacheHeadBytesHex = Hex.encodeHexString(cacheBytes).toUpperCase();
        log.debug("File HeadBytes hex is [{}]", cacheHeadBytesHex);

        List<FileType> matchedFileTypes = new ArrayList<>();
        FileType[] fileTypes = FileType.values();
        for (FileType fileType : fileTypes) {
            byte[] headBytes = new byte[fileType.headBytes()];
            System.arraycopy(cacheBytes, 0, headBytes, 0, fileType.headBytes());
            String hex = Hex.encodeHexString(headBytes).toUpperCase();

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
     * 获取文件MD5特征码
     *
     * @param file
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getFileMD5Hex(File file) throws NoSuchAlgorithmException, IOException {
        return digestFile(file, MessageDigest.getInstance("MD5"));
    }

    /**
     * 获取文件SHA256特征码
     *
     * @param file
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getFileSHA256Hex(File file) throws NoSuchAlgorithmException, IOException {
        return digestFile(file, MessageDigest.getInstance("SHA-256"));
    }


    /**
     * 获取文件摘要
     *
     * @param file
     * @param digest
     * @return
     * @throws IOException
     */
    public static String digestFile(File file, MessageDigest digest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        try (
                FileInputStream fis = new FileInputStream(file);
                FileChannel channel = fis.getChannel();
        ) {
            while (channel.read(buffer) != -1) {
                buffer.flip();
                digest.update(buffer);
                buffer.clear();
            }
            return new String(Hex.encodeHex(digest.digest()));
        }
    }

    /**
     * 文件本地下载
     *
     * @param url      文件链接
     * @param savePath 保存地址
     * @return
     * @throws IOException
     */
    /*public static File download(String url, File savePath) throws IOException {
        okhttp3.OkHttpClient httpClient = new okhttp3.OkHttpClient.Builder().build();
        try (okhttp3.Response response = httpClient.newCall(new okhttp3.Request.Builder().url(url).build()).execute()) {
            InputStream is = response.body().byteStream();
            String name = FilenameUtils.getName(url);

            File file = new File(savePath.getPath(), name);
            if (!savePath.exists()) {
                savePath.mkdirs();
            }
            IOUtils.copy(is, new FileOutputStream(file));
            return file;
        }
    }*/

}
