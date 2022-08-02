package io.github.liuruinian.im.server.controller.singlechat;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import io.github.liuruinian.im.core.params.singlechat.*;
import io.github.liuruinian.im.core.restapi.SingleChatApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuruinian
 * @version 2022-02-08
 * <p>
 *     单聊Controller
 * </p>
 */
@RestController
@Api(tags = "单聊消息API")
public class SingleChatController {

    private SingleChatApi singleChatApi;

    @Autowired
    public void setSingleChatApi(SingleChatApi singleChatApi) {
        this.singleChatApi = singleChatApi;
    }

    @ApiOperation("单发单聊消息")
    public JSONObject sendMsg(@RequestBody SingleIssueSingleChatParam singleIssueSingleChatParam) {
        Preconditions.checkNotNull(singleIssueSingleChatParam, "[SingleChatController] -> singleIssueSingleChatParam is null!");

        String responseBody = singleChatApi.sendMsg(singleIssueSingleChatParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("批量发单聊消息")
    public JSONObject batchSendMsg(@RequestBody BatchSingleChatParam batchSingleChatParam) {
        Preconditions.checkNotNull(batchSingleChatParam, "[SingleChatController] -> batchSingleChatParam is null!");

        String responseBody = singleChatApi.batchSendMsg(batchSingleChatParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("导入单聊消息")
    public JSONObject importMsg(@RequestBody ImportSingleChatParam importSingleChatParam) {
        Preconditions.checkNotNull(importSingleChatParam, "[SingleChatController] -> importSingleChatParam is null!");

        String responseBody = singleChatApi.importMsg(importSingleChatParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("查询单聊消息")
    public JSONObject admin_getroammsg(@RequestBody QuerySingleChatParam querySingleChatParam) {
        Preconditions.checkNotNull(querySingleChatParam, "[SingleChatController] -> querySingleChatParam is null!");

        String responseBody = singleChatApi.admin_getroammsg(querySingleChatParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("撤回单聊消息")
    public JSONObject admin_msgwithdraw(@RequestBody WithdrawSingleChatParam withdrawSingleChatParam) {
        Preconditions.checkNotNull(withdrawSingleChatParam, "[SingleChatController] -> withdrawSingleChatParam is null!");

        String responseBody = singleChatApi.admin_msgwithdraw(withdrawSingleChatParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("设置单聊消息已读")
    public JSONObject admin_set_msg_read(@RequestBody ReadSingleChatParam readSingleChatParam) {
        Preconditions.checkNotNull(readSingleChatParam, "[SingleChatController] -> readSingleChatParam is null!");

        String responseBody = singleChatApi.admin_set_msg_read(readSingleChatParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("查询单聊未读消息计数")
    public JSONObject get_c2c_unread_msg_num(@RequestBody C2CUnreadMsgNumParam c2CUnreadMsgNumParam) {
        Preconditions.checkNotNull(c2CUnreadMsgNumParam, "[SingleChatController] -> c2CUnreadMsgNumParam is null!");

        String responseBody = singleChatApi.get_c2c_unread_msg_num(c2CUnreadMsgNumParam);

        return JSONObject.parseObject(responseBody);
    }
}
