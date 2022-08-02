package io.github.liuruinian.im.server.controller.relationchain;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import io.github.liuruinian.im.core.params.relationchain.*;
import io.github.liuruinian.im.core.restapi.RelationChainManageApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "关系链管理API")
public class RelationChainController {

    private RelationChainManageApi relationChainManageApi;

    @Autowired
    public void setRelationChainManageApi(RelationChainManageApi relationChainManageApi) {
        this.relationChainManageApi = relationChainManageApi;
    }

    @ApiOperation("添加好友")
    public JSONObject friendAdd(@RequestBody FriendAddParam friendAddParam) {
        Preconditions.checkNotNull(friendAddParam, "[RelationChainController] -> friendAddParam is null!");

        String responseBody = relationChainManageApi.friend_add(friendAddParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("导入好友")
    public JSONObject friendImport(@RequestBody FriendImportParam friendImportParam) {
        Preconditions.checkNotNull(friendImportParam, "[RelationChainController] -> friendImportParam is null!");

        String responseBody = relationChainManageApi.friend_import(friendImportParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("更新好友")
    public JSONObject friendUpdate(@RequestBody FriendUpdateParam friendUpdateParam) {
        Preconditions.checkNotNull(friendUpdateParam, "[RelationChainController] -> friendUpdateParam is null!");

        String responseBody = relationChainManageApi.friend_update(friendUpdateParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("删除好友")
    public JSONObject friendDelete(@RequestBody FriendDeleteParam friendDeleteParam) {
        Preconditions.checkNotNull(friendDeleteParam, "[RelationChainController] -> friendDeleteParam is null!");

        String responseBody = relationChainManageApi.friend_delete(friendDeleteParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("删除所有好友")
    public JSONObject friendDeleteAll(@RequestBody FriendDeleteAllParam friendDeleteAllParam) {
        Preconditions.checkNotNull(friendDeleteAllParam, "[RelationChainController] -> friendDeleteAllParam is null!");

        String responseBody = relationChainManageApi.friend_delete_all(friendDeleteAllParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("检验好友")
    public JSONObject friendCheck(@RequestBody FriendCheckParam friendCheckParam) {
        Preconditions.checkNotNull(friendCheckParam, "[RelationChainController] -> friendCheckParam is null!");

        String responseBody = relationChainManageApi.friend_check(friendCheckParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("拉取好友")
    public JSONObject friendGet(@RequestBody FriendGetParam friendGetParam) {
        Preconditions.checkNotNull(friendGetParam, "[RelationChainController] -> friendGetParam is null!");

        String responseBody = relationChainManageApi.friend_get(friendGetParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("拉取指定好友资料和数据")
    public JSONObject friendGetList(@RequestBody FriendGetListParam friendGetListParam) {
        Preconditions.checkNotNull(friendGetListParam, "[RelationChainController] -> friendGetListParam is null!");

        String responseBody = relationChainManageApi.friend_get_list(friendGetListParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("添加黑名单")
    public JSONObject blackListAdd(@RequestBody BlackListAddParam blackListAddParam) {
        Preconditions.checkNotNull(blackListAddParam, "[RelationChainController] -> blackListAddParam is null!");

        String responseBody = relationChainManageApi.black_list_add(blackListAddParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("删除黑名单")
    public JSONObject blackListDelete(@RequestBody BlackListDeleteParam blackListDeleteParam) {
        Preconditions.checkNotNull(blackListDeleteParam, "[RelationChainController] -> blackListDeleteParam is null!");

        String responseBody = relationChainManageApi.black_list_delete(blackListDeleteParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("拉取黑名单")
    public JSONObject blackListGet(@RequestBody BlackListGetParam blackListGetParam) {
        Preconditions.checkNotNull(blackListGetParam, "[RelationChainController] -> blackListGetParam is null!");

        String responseBody = relationChainManageApi.black_list_get(blackListGetParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("校验黑名单")
    public JSONObject blackListCheck(@RequestBody BlackListCheckParam blackListCheckParam) {
        Preconditions.checkNotNull(blackListCheckParam, "[RelationChainController] -> blackListCheckParam is null!");

        String responseBody = relationChainManageApi.black_list_check(blackListCheckParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("添加分组")
    public JSONObject groupAdd(@RequestBody GroupAddParam groupAddParam) {
        Preconditions.checkNotNull(groupAddParam, "[RelationChainController] -> groupAddParam is null!");

        String responseBody = relationChainManageApi.group_add(groupAddParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("删除分组")
    public JSONObject groupDelete(@RequestBody GroupDeleteParam groupDeleteParam) {
        Preconditions.checkNotNull(groupDeleteParam, "[RelationChainController] -> groupDeleteParam is null!");

        String responseBody = relationChainManageApi.group_delete(groupDeleteParam);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("拉取分组")
    public JSONObject groupGet(@RequestBody GroupGetParam groupGetParam) {
        Preconditions.checkNotNull(groupGetParam, "[RelationChainController] -> groupGetParam is null!");

        String responseBody = relationChainManageApi.group_get(groupGetParam);

        return JSONObject.parseObject(responseBody);
    }
}
