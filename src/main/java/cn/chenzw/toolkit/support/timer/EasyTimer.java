package cn.chenzw.toolkit.support.timer;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 简易计时器
 *
 * @author chenzw
 */
public class EasyTimer {

    private ScheduledExecutorService executor;

    private Duration timeout;

    private volatile boolean isTimeout = false;


    public EasyTimer(Duration timeout) {
        this.timeout = timeout;
        this.executor = this.createExecutorService();
    }

    /**
     * 启动计时器
     */
    public void start() {
        this.start(this.timeout);
    }


    /**
     * 重新计时
     */
    public void restart() {
        this.restart(this.timeout);
    }

    public void restart(Duration timeout) {
        this.isTimeout = false;
        this.executor.shutdownNow();
        this.executor = createExecutorService();
        this.start(timeout);
    }

    /**
     * 是否已超时
     *
     * @return
     */
    public boolean isTimeout() {
        return isTimeout;
    }

    private void start(Duration timeout) {
        this.executor.schedule(() -> {
            this.isTimeout = true;
            this.executor.shutdown();
        }, timeout.toMillis(), TimeUnit.MILLISECONDS);
    }

    private ScheduledExecutorService createExecutorService() {
        return Executors.newScheduledThreadPool(1);
    }

}
