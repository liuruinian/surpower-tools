package io.github.liuruinian.im.server.controller.recentcontacts;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import io.github.liuruinian.im.core.params.recentcontacts.DeleteSessionParam;
import io.github.liuruinian.im.core.params.recentcontacts.GetListParam;
import io.github.liuruinian.im.core.restapi.RecentContactsApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "最近联系人API")
public class RecentContactsController {

    private RecentContactsApi recentContactsApi;

    @Autowired
    public void setRecentContactsApi(RecentContactsApi recentContactsApi) {
        this.recentContactsApi = recentContactsApi;
    }

    @ApiOperation("拉取会话列表")
    public JSONObject getList(@RequestBody GetListParam getListParam) {
        Preconditions.checkNotNull(getListParam, "[RecentContactsController] -> getListParam is null!");

        String responseBody = recentContactsApi.get_list(getListParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("删除会话")
    public JSONObject deleteSession(@RequestBody DeleteSessionParam deleteSessionParam) {
        Preconditions.checkNotNull(deleteSessionParam, "[RecentContactsController] -> deleteSessionParam is null!");

        String responseBody = recentContactsApi.delete(deleteSessionParam);

        return JSONObject.parseObject(responseBody);
    }
}
