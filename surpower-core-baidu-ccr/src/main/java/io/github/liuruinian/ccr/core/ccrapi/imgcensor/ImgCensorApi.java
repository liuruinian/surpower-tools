package io.github.liuruinian.ccr.core.ccrapi.imgcensor;

import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.ccr.core.param.ImgCensorParam;

/**
 * img censor
 *
 * @author liuruinian
 * @version 2022-12-08
 */
public interface ImgCensorApi {

    /**
     * 图像审核
     *
     * @param param 图像审核请求参数
     * @return 响应结果
     */
    JSONObject imgCensor(ImgCensorParam param);
}
