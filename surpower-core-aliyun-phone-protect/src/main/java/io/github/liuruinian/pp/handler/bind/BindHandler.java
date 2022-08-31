package io.github.liuruinian.pp.handler.bind;

import com.aliyuncs.dyplsapi.model.v20170525.BindAxnResponse;
import io.github.liuruinian.pp.domain.AxnBindParam;

/**
 * 绑定处理器
 */
public interface BindHandler {

    /**
     * 添加AXN号码的绑定关系
     *
     * @param axnBindParam Axn绑定参数
     */
    BindAxnResponse bindAxn(AxnBindParam axnBindParam);
}
