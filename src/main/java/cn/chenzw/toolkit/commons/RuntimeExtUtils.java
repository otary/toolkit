package cn.chenzw.toolkit.commons;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;


/**
 * @author chenzw
 */
public class RuntimeExtUtils {


    /**
     * 执行命令，返回字符串
     *
     * @param cmds
     * @return
     * @throws IOException
     */
    public static String execForStr(String... cmds) throws IOException {
        Charset charset = Charset.defaultCharset();
        if (SystemUtils.IS_OS_WINDOWS) {
            charset = Charset.forName("GBK");
        }
        return execForStr(charset, cmds);
    }

    public static String execForStr(Charset charset, String... cmds) throws IOException {
        return getStringProcessResult(exec(cmds), charset);
    }

    /**
     * 执行命令，返回列表
     *
     * @param cmds
     * @return
     */
    public static List<String> execForLines(String... cmds) throws IOException {
        Charset charset = Charset.defaultCharset();
        if (SystemUtils.IS_OS_WINDOWS) {
            charset = Charset.forName("GBK");
        }
        return execForLines(charset, cmds);
    }


    public static List<String> execForLines(Charset charset, String... cmds) throws IOException {
        return getLinesProcessResult(exec(cmds), charset);
    }

    /**
     * 执行命令
     *
     * <pre>
     *     RuntimeExtUtils.exec("ipconfig /all");
     *     RuntimeExtUtils.exec("ipconfig", "/all");
     * </pre>
     *
     * @param cmds
     * @return
     * @throws IOException
     */
    public static Process exec(String... cmds) throws IOException {
        cmds = doInitCmdArgs(cmds);
        return new ProcessBuilder(cmds).redirectErrorStream(true).start();
    }

    /**
     * 执行命令
     *
     * @param envp 环境变量参数（格式: key=value）,null表示继承系统环境变量
     * @param cmds
     * @return
     * @throws IOException
     */
    public static Process exec(String[] envp, String... cmds) throws IOException {
        cmds = doInitCmdArgs(cmds);
        return Runtime.getRuntime().exec(cmds, envp);
    }

    private static String[] doInitCmdArgs(String... cmds) {
        if (ArrayUtils.isEmpty(cmds)) {
            throw new NullPointerException("Command is empty!");
        }

        if (cmds.length == 1) {
            if (SystemUtils.IS_OS_WINDOWS) {
                cmds = StringUtils.split(cmds[0], (char) Character.SPACE_SEPARATOR);
            } else {
                cmds = ArrayUtils.addAll(new String[]{"sh", "-c"}, cmds);
            }
        }
        return cmds;
    }

    /**
     * 获取命令执行结果（字符串格式）
     *
     * @param process
     * @param charset
     * @return
     * @throws IOException
     */
    public static String getStringProcessResult(Process process, Charset charset) throws IOException {
        try (InputStream in = process.getInputStream()) {
            return IOUtils.toString(in, charset);
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

    /**
     * 获取命令执行结果（列表）
     *
     * @param process
     * @param charset
     * @return
     * @throws IOException
     */
    public static List<String> getLinesProcessResult(Process process, Charset charset) throws IOException {
        try (InputStream in = process.getInputStream()) {
            return IOUtils.readLines(in, charset);
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

    /**
     * 销毁进程
     *
     * @param process 进程
     */
    public static void destroy(Process process) {
        if (null != process) {
            process.destroy();
        }
    }


}
