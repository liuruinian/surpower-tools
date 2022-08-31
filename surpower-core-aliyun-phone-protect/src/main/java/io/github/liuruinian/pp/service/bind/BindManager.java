package io.github.liuruinian.pp.service.bind;

import com.aliyuncs.dyplsapi.model.v20170525.BindAxnResponse;
import io.github.liuruinian.pp.domain.AxnBindParam;

/**
 * @author liuruinian
 * <p>
 *     号码绑定
 * </p>
 */
public interface BindManager {

    /**
     * 添加AXN号码的绑定关系
     *
     * @param axnBindParam Axn绑定参数
     */
    BindAxnResponse bindAxn(AxnBindParam axnBindParam);
}
