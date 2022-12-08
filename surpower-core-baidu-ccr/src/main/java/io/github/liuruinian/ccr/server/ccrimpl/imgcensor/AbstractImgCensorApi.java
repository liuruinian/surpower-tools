package io.github.liuruinian.ccr.server.ccrimpl.imgcensor;

import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.ccr.core.ccrapi.imgcensor.ImgCensorApi;
import io.github.liuruinian.ccr.core.param.ImgCensorParam;

public abstract class AbstractImgCensorApi implements ImgCensorApi {

    @Override
    public JSONObject imgCensor(ImgCensorParam param) {
        return handleImgCensor(param);
    }

    protected abstract JSONObject handleImgCensor(ImgCensorParam param);
}
