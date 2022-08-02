package io.github.liuruinian.im.server.api.relationchain;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.im.core.constant.TencentCloudImApiConstant;
import io.github.liuruinian.im.core.params.relationchain.*;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public class DefaultRelationChainApi extends AbstractRelationChainApi {

    public DefaultRelationChainApi(ImServerProperties imServerProperties,
                                   UserSignService userSignService,
                                   ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService, eventPublisher);
    }

    @Override
    protected String handleFriendAdd(FriendAddParam friendAddParam) {
        String friendAddApi = TencentCloudImApiConstant.RelationChainManage.FRIEND_ADD;

        String requestUrl = obtainRequestUrl(friendAddApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_add request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(friendAddParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_add request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRelationChainApi] -> friend_add response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleFriendImport(FriendImportParam friendImportParam) {
        String friendImportApi = TencentCloudImApiConstant.RelationChainManage.FRIEND_IMPORT;

        String requestUrl = obtainRequestUrl(friendImportApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_import request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(friendImportParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_import request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRelationChainApi] -> friend_import response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleFriendUpdate(FriendUpdateParam friendUpdateParam) {
        String friendUpdateApi = TencentCloudImApiConstant.RelationChainManage.FRIEND_UPDATE;

        String requestUrl = obtainRequestUrl(friendUpdateApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_update request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(friendUpdateParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_update request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRelationChainApi] -> friend_update response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleFriendDelete(FriendDeleteParam friendDeleteParam) {
        String friendDeleteApi = TencentCloudImApiConstant.RelationChainManage.FRIEND_DELETE;

        String requestUrl = obtainRequestUrl(friendDeleteApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_delete request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(friendDeleteParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_delete request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRelationChainApi] -> friend_delete response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleFriendDeleteAll(FriendDeleteAllParam friendDeleteAllParam) {
        String friendDeleteAllApi = TencentCloudImApiConstant.RelationChainManage.FRIEND_DELETE_ALL;

        String requestUrl = obtainRequestUrl(friendDeleteAllApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_delete_all request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(friendDeleteAllParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_delete_all request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRelationChainApi] -> friend_delete_all response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleFriendCheck(FriendCheckParam friendCheckParam) {
        String friendCheckApi = TencentCloudImApiConstant.RelationChainManage.FRIEND_CHECK;

        String requestUrl = obtainRequestUrl(friendCheckApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_check request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(friendCheckParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_check request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRelationChainApi] -> friend_check response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleFriendGet(FriendGetParam friendGetParam) {
        String friendGetApi = TencentCloudImApiConstant.RelationChainManage.FRIEND_GET;

        String requestUrl = obtainRequestUrl(friendGetApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_get request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(friendGetParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_get request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRelationChainApi] -> friend_get response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleFriendGetList(FriendGetListParam friendGetListParam) {
        String friendGetListApi = TencentCloudImApiConstant.RelationChainManage.FRIEND_GET_LIST;

        String requestUrl = obtainRequestUrl(friendGetListApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_get_list request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(friendGetListParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> friend_get_list request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRelationChainApi] -> friend_get_list response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleBlackListAdd(BlackListAddParam blackListAddParam) {
        String blackListAddApi = TencentCloudImApiConstant.RelationChainManage.BLACK_LIST_ADD;

        String requestUrl = obtainRequestUrl(blackListAddApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> black_list_add request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(blackListAddParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> black_list_add request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRelationChainApi] -> black_list_add response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleBlackListDelete(BlackListDeleteParam blackListDeleteParam) {
        String blackListDeleteApi = TencentCloudImApiConstant.RelationChainManage.BLACK_LIST_DELETE;

        String requestUrl = obtainRequestUrl(blackListDeleteApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> black_list_delete request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(blackListDeleteParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> black_list_delete request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRelationChainApi] -> black_list_delete response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleBlackListGet(BlackListGetParam blackListGetParam) {
        String blackListGetApi = TencentCloudImApiConstant.RelationChainManage.BLACK_LIST_GET;

        String requestUrl = obtainRequestUrl(blackListGetApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> black_list_get request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(blackListGetParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> black_list_get request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRelationChainApi] -> black_list_get response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleBlackListCheck(BlackListCheckParam blackListCheckParam) {
        String blackListCheckApi = TencentCloudImApiConstant.RelationChainManage.BLACK_LIST_CHECK;

        String requestUrl = obtainRequestUrl(blackListCheckApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> black_list_check request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(blackListCheckParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> black_list_check request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRelationChainApi] -> black_list_check response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleGroupAdd(GroupAddParam groupAddParam) {
        String groupAddApi = TencentCloudImApiConstant.RelationChainManage.GROUP_ADD;

        String requestUrl = obtainRequestUrl(groupAddApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> group_add request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(groupAddParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> group_add request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRelationChainApi] -> group_add response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleGroupDelete(GroupDeleteParam groupDeleteParam) {
        String friendDeleteApi = TencentCloudImApiConstant.RelationChainManage.GROUP_DELETE;

        String requestUrl = obtainRequestUrl(friendDeleteApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> group_delete request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(groupDeleteParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> group_delete request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRelationChainApi] -> group_delete response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleGroupGet(GroupGetParam groupGetParam) {
        String groupGetApi = TencentCloudImApiConstant.RelationChainManage.GROUP_GET;

        String requestUrl = obtainRequestUrl(groupGetApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> group_get request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(groupGetParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRelationChainApi] -> group_get request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRelationChainApi] -> group_get response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
