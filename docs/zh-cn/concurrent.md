## concurrent


### MutextExecutor

- 互斥执行器（统一时间只能执行一个）

``` java
MutexExecutor mutexExecutor = new MutexExecutor();
mutexExecutor.execute(() -> {
    logger.info("线程{}获取到锁,开始执行...", i);
}, () -> {
    logger.info("线程{}未获取到锁!", i);
});
```