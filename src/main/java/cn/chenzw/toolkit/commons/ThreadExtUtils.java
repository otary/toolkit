package cn.chenzw.toolkit.commons;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * 线程工具类
 *
 * @author chenzw
 */
public class ThreadExtUtils {

    private ThreadExtUtils() {
    }

    /**
     * 获取当前项目进程ID
     *
     * @return
     */
    public static int getProcessId() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return Integer.valueOf(runtimeMXBean.getName().split("@")[0])
                .intValue();
    }


    /**
     * 获取当前的栈元素信息
     *
     * @return
     * @see {@link StackTraceElement#getFileName()}:当前执行的文件名
     * @see {@link StackTraceElement#getClassName()}:当前执行的类名
     * @see {@link StackTraceElement#getMethodName()}:当前执行的方法名
     * @see {@link StackTraceElement#getLineNumber()}:当前执行的行号
     */
    public static StackTraceElement getCurrentStackElement() {
        return Thread.currentThread().getStackTrace()[1];
    }

}
