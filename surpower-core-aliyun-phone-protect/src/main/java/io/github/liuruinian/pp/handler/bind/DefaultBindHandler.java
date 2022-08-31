package io.github.liuruinian.pp.handler.bind;

import com.aliyuncs.dyplsapi.model.v20170525.BindAxnResponse;
import io.github.liuruinian.pp.domain.AxnBindParam;
import io.github.liuruinian.pp.properties.AsyncProperties;
import io.github.liuruinian.pp.service.bind.BindManager;
import io.github.liuruinian.pp.threadpool.AsyncThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.Future;

@Component
public class DefaultBindHandler implements BindHandler {

    private BindManager                 bindManager;

    private AsyncProperties             properties;

    private AsyncThreadPoolExecutor     asyncThreadPoolExecutor;

    @Autowired
    public void setBindManager(BindManager bindManager) {
        this.bindManager = bindManager;
    }

    @Autowired
    public void setProperties(AsyncProperties properties) {
        this.properties = properties;
    }

    @Autowired
    public void setAsyncThreadPoolExecutor(AsyncThreadPoolExecutor asyncThreadPoolExecutor) {
        this.asyncThreadPoolExecutor = asyncThreadPoolExecutor;
    }

    @Override
    public BindAxnResponse bindAxn(AxnBindParam axnBindParam) {
        try {
            if (properties.isEnable()) {
                Future<BindAxnResponse> future = asyncThreadPoolExecutor.submit(() -> bindManager.bindAxn(axnBindParam));
                return future.get();
            } else {
                return bindManager.bindAxn(axnBindParam);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
