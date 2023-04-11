package cn.chenzw.toolkit.concurrent;


import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 互斥执行器
 *
 * @author chenzw
 */
public class MutexExecutor {

    private Lock lock;

    public MutexExecutor() {
        this.lock = new ReentrantLock();
    }

    public MutexExecutor(Lock lock) {
        this.lock = lock;
    }

    /**
     * @param getLockCallback  获取到锁的回调函数
     * @param failLockCallback 获取不到锁的回调函数
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T execute(Callable<T> getLockCallback, Callable<T> failLockCallback) throws Exception {
        if (!lock.tryLock()) {
            return failLockCallback.call();
        }
        try {
            return getLockCallback.call();
        } finally {
            lock.unlock();
        }
    }

    /**
     * @param getLockCallback  获取锁回调函数
     * @param failLockCallback 获取不到锁回调函数
     */
    public void execute(Runnable getLockCallback, Runnable failLockCallback) {
        if (!lock.tryLock()) {
            failLockCallback.run();
            return;
        }
        try {
            getLockCallback.run();
        } finally {
            lock.unlock();
        }
    }
}
