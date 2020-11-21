package cn.chenzw.toolkit.concurrent;

import java.util.List;
import java.util.concurrent.Future;

/**
 * 线程池工具类
 *
 * @author chenzw
 */
public class ThreadPoolUtils {

    /**
     * 任务是否已全部完成
     *
     * @param futures
     * @return
     */
    public static boolean isAllDone(List<Future<?>> futures) {
        for (Future<?> future : futures) {
            if (!future.isDone()) {
                return false;
            }
        }
        return true;
    }
}
