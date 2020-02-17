package cn.chenzw.toolkit.concurrent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RunWith(JUnit4.class)
public class MutexExecutorTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 无返回值
     *
     * @throws InterruptedException
     */
    @Test
    public void testExecutor() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        MutexExecutor mutexExecutor = new MutexExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Mutext(i, mutexExecutor));
        }

        Thread.sleep(3000);
    }

    /**
     * 带返回值
     */
    @Test
    public void testExecutorWithCallable() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        MutexExecutor mutexExecutor = new MutexExecutor();

        ConcurrentLinkedQueue<Future> queue = new ConcurrentLinkedQueue<>();
        List<String> results = new ArrayList();
        for (int i = 0; i < 5; i++) {
            Future futrue = executorService.submit(new MutextWithCallable(i, mutexExecutor));
            queue.add(futrue);
        }

        Future<String> item;
        while ((item = queue.poll()) != null) {
            if (item.isDone()) {
                if (!item.isCancelled()) {
                    results.add(item.get());
                }
            } else {
                queue.add(item);
            }
        }

        logger.info("结果:" + results);

        Thread.sleep(3000);
    }


    public class Mutext implements Runnable {

        private int threadSeq;
        private MutexExecutor mutexExecutor;

        public Mutext(int threadSeq, MutexExecutor mutexExecutor) {
            this.threadSeq = threadSeq;
            this.mutexExecutor = mutexExecutor;
        }

        @Override
        public void run() {
            mutexExecutor.execute(() -> {
                logger.info("线程{}获取到锁,开始执行...", threadSeq);
            }, () -> {
                logger.info("线程{}未获取到锁!", threadSeq);
            });
        }
    }

    public class MutextWithCallable implements Callable {

        private int threadSeq;
        private MutexExecutor mutexExecutor;

        public MutextWithCallable(int threadSeq, MutexExecutor mutexExecutor) {
            this.threadSeq = threadSeq;
            this.mutexExecutor = mutexExecutor;
        }

        @Override
        public Object call() throws Exception {
            return mutexExecutor.execute(() -> {
                logger.info("线程{}获取到锁,开始执行...", threadSeq);
                return "执行完毕！";
            }, () -> {
                logger.info("线程{}未获取到锁!", threadSeq);
                return "执行中...";
            });
        }
    }

}
