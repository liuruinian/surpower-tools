package io.github.liuruinian.pp.threadpool;

import io.github.liuruinian.pp.properties.AsyncProperties;
import io.github.liuruinian.pp.properties.RejectPolicy;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuruinian
 * aliyun号码隐私保护异步处理线程池
 */
public abstract class AbstractAsyncThreadPoolExecutor implements AsyncThreadPoolExecutor {

    protected final AsyncProperties properties;

    public AbstractAsyncThreadPoolExecutor(AsyncProperties properties) {
        this.properties = properties;
    }

    /**
     * 根据拒绝策略构造拒绝处理程序
     *
     * @param type
     *         拒绝策略
     * @return 拒绝处理程序
     */
    protected static RejectedExecutionHandler buildRejectedExecutionHandler(RejectPolicy type) {
        if (type == null) {
            return new ThreadPoolExecutor.AbortPolicy();
        }
        switch (type) {
            case Caller:
                return new ThreadPoolExecutor.CallerRunsPolicy();
            case Discard:
                return new ThreadPoolExecutor.DiscardPolicy();
            case DiscardOldest:
                return new ThreadPoolExecutor.DiscardOldestPolicy();
            default:
                return new ThreadPoolExecutor.AbortPolicy();
        }
    }

    /**
     * 提交异步任务
     * @param command 待执行任务
     */
    @Override
    public final void execute(Runnable command) {
        if (command != null) {
            submit0(command);
        }
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        RunnableFuture<T> ftask = newTaskFor(task);
        ftask.run();
        return ftask;
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new FutureTask<T>(callable);
    }

    /**
     * 提交异步任务
     * @param command 待执行任务
     */
    protected abstract void submit0(Runnable command);

    /**
     * 默认线程工厂
     */
    protected static class DefaultThreadFactory implements ThreadFactory {

        private final ThreadGroup group;

        private final AtomicInteger threadNumber = new AtomicInteger(1);

        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "phone-number-async-thread-";
        }

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(group, runnable, namePrefix + threadNumber.getAndIncrement(), 0);
            thread.setDaemon(false);
            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    }
}
