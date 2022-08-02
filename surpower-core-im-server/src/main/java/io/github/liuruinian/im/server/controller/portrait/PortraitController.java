package io.github.liuruinian.im.server.controller.portrait;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import io.github.liuruinian.im.core.params.portrait.PortraitGetParam;
import io.github.liuruinian.im.core.params.portrait.PortraitSetParam;
import io.github.liuruinian.im.core.restapi.PortraitManageApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "资料管理API")
public class PortraitController {

    private PortraitManageApi portraitManageApi;

    @Autowired
    public void setPortraitManageApi(PortraitManageApi portraitManageApi) {
        this.portraitManageApi = portraitManageApi;
    }

    @ApiOperation("资料设置")
    public JSONObject portrait_set(@RequestBody PortraitSetParam portraitSetParam) {
        Preconditions.checkNotNull(portraitSetParam, "[PortraitController] -> portraitSetParam is null!");

        String responseBody = portraitManageApi.portrait_set(portraitSetParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("拉取资料")
    public JSONObject portrait_get(@RequestBody PortraitGetParam portraitGetParam) {
        Preconditions.checkNotNull(portraitGetParam, "[PortraitController] -> portraitGetParam is null!");

        String responseBody = portraitManageApi.portrait_get(portraitGetParam);

        return JSONObject.parseObject(responseBody);
    }
}
