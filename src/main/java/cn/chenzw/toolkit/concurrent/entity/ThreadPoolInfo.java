package cn.chenzw.toolkit.concurrent.entity;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池信息
 *
 * @author chenzw
 */
public class ThreadPoolInfo {

    private int activeCount;

    private int poolSize;

    private long taskCount;

    private int corePoolSize;

    private long completedTaskCount;

    private int largestPoolSize;

    private int maximumPoolSize;

    private long keepAliveTime;

    private int queueSize;

    private boolean isShutdown;

    private boolean isTerminated;

    private boolean isTerminating;

    private boolean prestartCoreThread;

    private int prestartAllCoreThreads;

    public ThreadPoolInfo(ThreadPoolExecutor threadPoolExecutor) {
        this.activeCount = threadPoolExecutor.getActiveCount();
        this.poolSize = threadPoolExecutor.getPoolSize();
        this.taskCount = threadPoolExecutor.getTaskCount();
        this.corePoolSize = threadPoolExecutor.getCorePoolSize();
        this.completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
        this.largestPoolSize = threadPoolExecutor.getLargestPoolSize();
        this.maximumPoolSize = threadPoolExecutor.getMaximumPoolSize();
        this.keepAliveTime = threadPoolExecutor.getKeepAliveTime(TimeUnit.MILLISECONDS);
        this.queueSize = threadPoolExecutor.getQueue().size();
        this.isShutdown = threadPoolExecutor.isShutdown();
        this.isTerminated = threadPoolExecutor.isTerminated();
        this.isTerminating = threadPoolExecutor.isTerminating();
        this.prestartCoreThread = threadPoolExecutor.prestartCoreThread();
        this.prestartAllCoreThreads = threadPoolExecutor.prestartAllCoreThreads();
    }


    public int getActiveCount() {
        return activeCount;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public long getTaskCount() {
        return taskCount;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public long getCompletedTaskCount() {
        return completedTaskCount;
    }

    public int getLargestPoolSize() {
        return largestPoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public boolean isShutdown() {
        return isShutdown;
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public boolean isTerminating() {
        return isTerminating;
    }

    public boolean isPrestartCoreThread() {
        return prestartCoreThread;
    }

    public int getPrestartAllCoreThreads() {
        return prestartAllCoreThreads;
    }

    @Override
    public String toString() {
        return "ThreadPoolInfo{" +
                "activeCount=" + activeCount +
                ", poolSize=" + poolSize +
                ", taskCount=" + taskCount +
                ", corePoolSize=" + corePoolSize +
                ", completedTaskCount=" + completedTaskCount +
                ", largestPoolSize=" + largestPoolSize +
                ", maximumPoolSize=" + maximumPoolSize +
                ", keepAliveTime=" + keepAliveTime +
                ", queueSize=" + queueSize +
                ", isShutdown=" + isShutdown +
                ", isTerminated=" + isTerminated +
                ", isTerminating=" + isTerminating +
                ", prestartCoreThread=" + prestartCoreThread +
                ", prestartAllCoreThreads=" + prestartAllCoreThreads +
                '}';
    }
}
