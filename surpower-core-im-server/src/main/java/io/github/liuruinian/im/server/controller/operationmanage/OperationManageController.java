package io.github.liuruinian.im.server.controller.operationmanage;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import io.github.liuruinian.im.core.params.operationmanage.GetAppInfoParam;
import io.github.liuruinian.im.core.params.operationmanage.GetHistoryMsgParam;
import io.github.liuruinian.im.core.restapi.OperationManageApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "运营管理API")
public class OperationManageController {

    private OperationManageApi operationManageApi;

    @Autowired
    public void setOperationManageApi(OperationManageApi operationManageApi) {
        this.operationManageApi = operationManageApi;
    }

    @ApiOperation("拉取运营数据")
    public JSONObject getAppInfo(@RequestBody GetAppInfoParam getAppInfoParam) {
        Preconditions.checkNotNull(getAppInfoParam, "[OperationManageController] -> getAppInfoParam is null!");

        String responseBody = operationManageApi.getappinfo(getAppInfoParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("下载最新消息记录")
    public JSONObject getHistoryMsg(@RequestBody GetHistoryMsgParam getHistoryMsgParam) {
        Preconditions.checkNotNull(getHistoryMsgParam, "[OperationManageController] -> getHistoryMsgParam is null!");

        String responseBody = operationManageApi.get_history(getHistoryMsgParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("获取服务器IP地址")
    public JSONObject getIpList() {
        String responseBody = operationManageApi.getIpList();

        return JSONObject.parseObject(responseBody);
    }
}
