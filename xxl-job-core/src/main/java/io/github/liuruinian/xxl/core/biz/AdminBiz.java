package io.github.liuruinian.xxl.core.biz;

import io.github.liuruinian.xxl.core.biz.model.HandleCallbackParam;
import io.github.liuruinian.xxl.core.biz.model.RegistryParam;
import io.github.liuruinian.xxl.core.biz.model.ReturnT;
import java.util.List;

/**
 * @author xuxueli 2017-07-27 21:52:49
 */
public interface AdminBiz {


    // ---------------------- callback ----------------------

    /**
     * callback
     *
     * @param callbackParamList callbackParamList
     * @return ReturnT
     */
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList);


    // ---------------------- registry ----------------------

    /**
     * registry
     *
     * @param registryParam registryParam
     * @return ReturnT
     */
    public ReturnT<String> registry(RegistryParam registryParam);

    /**
     * registry remove
     *
     * @param registryParam registryParam
     * @return ReturnT
     */
    public ReturnT<String> registryRemove(RegistryParam registryParam);


    // ---------------------- biz (custome) ----------------------
    // group、job ... manage

}
