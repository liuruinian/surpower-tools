package io.github.liuruinian.im.server.api.recentcontacts;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.im.core.constant.TencentCloudImApiConstant;
import io.github.liuruinian.im.core.params.recentcontacts.DeleteSessionParam;
import io.github.liuruinian.im.core.params.recentcontacts.GetListParam;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public class DefaultRecentContactsApi extends AbstractRecentContactsApi {

    public DefaultRecentContactsApi(ImServerProperties imServerProperties,
                                    UserSignService userSignService,
                                    ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService, eventPublisher);
    }

    @Override
    protected String handleGetList(GetListParam getListParam) {
        String getListApi = TencentCloudImApiConstant.RecentContacts.GET_LIST;

        String requestUrl = obtainRequestUrl(getListApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRecentContactsApi] -> get_list request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(getListParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRecentContactsApi] -> get_list request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRecentContactsApi] -> get_list response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleDeleteSession(DeleteSessionParam deleteSessionParam) {
        String deleteSessionApi = TencentCloudImApiConstant.RecentContacts.DELETE;

        String requestUrl = obtainRequestUrl(deleteSessionApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRecentContactsApi] -> delete session request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(deleteSessionApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultRecentContactsApi] -> delete session request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultRecentContactsApi] -> delete session response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
