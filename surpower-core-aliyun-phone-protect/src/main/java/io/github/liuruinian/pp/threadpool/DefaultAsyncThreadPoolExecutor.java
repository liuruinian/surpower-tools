package io.github.liuruinian.pp.threadpool;

import io.github.liuruinian.pp.properties.AsyncProperties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author liuruinian
 * 默认绑定异步处理线程池实现
 */
public class DefaultAsyncThreadPoolExecutor extends AbstractAsyncThreadPoolExecutor {

    private final ThreadPoolExecutor executor;

    public DefaultAsyncThreadPoolExecutor(AsyncProperties properties) {
        super(properties);
        executor = new ThreadPoolExecutor(
                properties.getCorePoolSize(),
                properties.getMaximumPoolSize(),
                properties.getKeepAliveTime(),
                properties.getUnit(),
                new LinkedBlockingQueue<>(properties.getQueueCapacity()),
                new DefaultThreadFactory(),
                buildRejectedExecutionHandler(properties.getRejectPolicy()));
    }

    @Override
    protected void submit0(Runnable command) {
        if (command == null) {
            return;
        }
        executor.execute(command);
    }
}
