package io.github.liuruinian.pp.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author liuruinian
 * 阿里云号码隐私绑定异步线程池
 */
public interface AsyncThreadPoolExecutor {

    /**
     * 执行异步任务
     */
    void execute(Runnable task);

    /**
     * 提交异步任务
     * @param task 异步任务
     */
    <T> Future<T> submit(Callable<T> task);
}
