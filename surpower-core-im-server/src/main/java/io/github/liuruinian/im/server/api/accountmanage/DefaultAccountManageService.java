package io.github.liuruinian.im.server.api.accountmanage;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.im.core.constant.TencentCloudImApiConstant;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Set;

@Slf4j
public class DefaultAccountManageService extends AbstractAccountManageApi {

    public DefaultAccountManageService(ImServerProperties imServerProperties,
                                       UserSignService userSignService,
                                       ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService, eventPublisher);
    }

    @Override
    public String handleAccountImport(String userId, String nick, String faceUrl) {
        final String accountImportApi = TencentCloudImApiConstant.AccountManage.ACCOUNT_IMPORT;

        String requestUrl = obtainRequestUrl(accountImportApi);
        if (log.isInfoEnabled()) {
            log.info("[AbstractAccountManageApi] -> account import request url : {}", requestUrl);
        }

        JSONObject requestBody = new JSONObject();
        requestBody.put("UserID", userId);
        requestBody.put("Nick", nick);
        requestBody.put("FaceUrl", faceUrl);

        try {
            String responseBody = HttpUtil.post(requestUrl, JSONObject.toJSONString(requestBody));

            if (log.isInfoEnabled()) {
                log.info("[AbstractAccountManageApi] -> account import response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleMultiAccountImport(List<String> accounts) {
        final String multiAccountImportApi = TencentCloudImApiConstant.AccountManage.MULTI_ACCOUNT_IMPORT;

        String requestUrl = obtainRequestUrl(multiAccountImportApi);
        if (log.isInfoEnabled()) {
            log.info("[AbstractAccountManageApi] -> multi account import request url : {}", requestUrl);
        }

        JSONObject requestBody = new JSONObject();
        requestBody.put("Accounts", accounts);

        try {
            String responseBody = HttpUtil.post(requestUrl, JSONObject.toJSONString(requestBody));
            if (log.isInfoEnabled()) {
                log.info("[AbstractAccountManageApi] -> multi account import response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleAccountDelete(Set<String> userIds) {
        final String accountDeleteApi = TencentCloudImApiConstant.AccountManage.ACCOUNT_DELETE;

        String requestUrl = obtainRequestUrl(accountDeleteApi);
        if (log.isInfoEnabled()) {
            log.info("[AbstractAccountManageApi] -> account delete request url : {}", requestUrl);
        }

        JSONArray deleteItems = new JSONArray();
        userIds.forEach(userId -> {
            JSONObject subItem = new JSONObject();
            subItem.put("UserID", userId);
            deleteItems.add(subItem);
        });


        JSONObject requestBody = new JSONObject();
        requestBody.put("DeleteItem", deleteItems);

        try {
            String responseBody = HttpUtil.post(requestUrl, JSONObject.toJSONString(requestBody));
            if (log.isInfoEnabled()) {
                log.info("[AbstractAccountManageApi] -> account delete response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleAccountCheckImport(Set<String> userIds) {
        final String accountCheckImportApi = TencentCloudImApiConstant.AccountManage.ACCOUNT_CHECK;

        String requestUrl = obtainRequestUrl(accountCheckImportApi);
        if (log.isInfoEnabled()) {
            log.info("[AbstractAccountManageApi] -> account check request url : {}", requestUrl);
        }

        JSONArray checkItems = new JSONArray();
        userIds.forEach(userId -> {
            JSONObject subItem = new JSONObject();
            subItem.put("UserID", userId);
            checkItems.add(subItem);
        });

        JSONObject requestBody = new JSONObject();
        requestBody.put("CheckItem", checkItems);

        try {
            String responseBody = HttpUtil.post(requestUrl, JSONObject.toJSONString(requestBody));
            if (log.isInfoEnabled()) {
                log.info("[AbstractAccountManageApi] -> account check response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleKick(String userId) {
        final String accountKickApi = TencentCloudImApiConstant.AccountManage.ACCOUNT_KICK;

        String requestUrl = obtainRequestUrl(accountKickApi);
        if (log.isInfoEnabled()) {
            log.info("[AbstractAccountManageApi] -> account kick request url : {}", requestUrl);
        }

        JSONObject requestBody = new JSONObject();
        requestBody.put("UserID", userId);

        try {
            String responseBody = HttpUtil.post(requestUrl, JSONObject.toJSONString(requestBody));
            if (log.isInfoEnabled()) {
                log.info("[AbstractAccountManageApi] -> account kick response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleQueryOnlineStatus(Set<String> userIds, Integer isNeedDetail) {
        String accountQueryStateApi = TencentCloudImApiConstant.AccountManage.ACCOUNT_QUERY_STATE;

        String requestUrl = obtainRequestUrl(accountQueryStateApi);
        if (log.isInfoEnabled()) {
            log.info("[AbstractAccountManageApi] -> query online status request url : {}", requestUrl);
        }

        JSONObject requestBody = new JSONObject();
        requestBody.put("IsNeedDetail", isNeedDetail);
        requestBody.put("To_Account", userIds);

        try {
            String responseBody = HttpUtil.post(requestUrl, JSONObject.toJSONString(requestBody));
            if (log.isInfoEnabled()) {
                log.info("[AbstractAccountManageApi] -> query online status response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
