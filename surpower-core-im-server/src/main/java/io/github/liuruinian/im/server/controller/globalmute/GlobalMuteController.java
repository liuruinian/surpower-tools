package io.github.liuruinian.im.server.controller.globalmute;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import io.github.liuruinian.im.core.params.globalmute.GetNoSpeakingParam;
import io.github.liuruinian.im.core.params.globalmute.SetNoSpeakingParam;
import io.github.liuruinian.im.core.restapi.GlobalMuteManageApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "全局禁言管理API")
public class GlobalMuteController {

    private GlobalMuteManageApi globalMuteManageApi;

    @Autowired
    public void setGlobalMuteManageApi(GlobalMuteManageApi globalMuteManageApi) {
        this.globalMuteManageApi = globalMuteManageApi;
    }

    @ApiOperation("设置全局禁言")
    public JSONObject setnospeaking(@RequestBody SetNoSpeakingParam setNoSpeakingParam) {
        Preconditions.checkNotNull(setNoSpeakingParam, "[GlobalMuteController] -> setNoSpeakingParam is null!");

        String responseBody = globalMuteManageApi.setnospeaking(setNoSpeakingParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("查询全局禁言")
    public JSONObject getnospeaking(@RequestBody GetNoSpeakingParam getNoSpeakingParam) {
        Preconditions.checkNotNull(getNoSpeakingParam, "[GlobalMuteController] -> getNoSpeakingParam is null!");

        String responseBody = globalMuteManageApi.getnospeaking(getNoSpeakingParam);

        return JSONObject.parseObject(responseBody);
    }
}
