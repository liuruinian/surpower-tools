package io.github.liuruinian.im.core.restapi;

import io.github.liuruinian.im.core.params.portrait.PortraitGetParam;
import io.github.liuruinian.im.core.params.portrait.PortraitSetParam;

/**
 * @author liuruinian
 * @version 2022-02-10
 * <p>
 *     资料管理API
 * </p>
 */
public interface PortraitManageApi {

    /**
     * 设置资料
     *
     * @param portraitSetParam 设置资料参数
     */
    String portrait_set(PortraitSetParam portraitSetParam);

    /**
     * 拉取资料
     *
     * @param portraitGetParam 拉取资料参数
     */
    String portrait_get(PortraitGetParam portraitGetParam);
}
