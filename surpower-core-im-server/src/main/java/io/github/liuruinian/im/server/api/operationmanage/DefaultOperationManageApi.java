package io.github.liuruinian.im.server.api.operationmanage;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.im.core.constant.TencentCloudImApiConstant;
import io.github.liuruinian.im.core.params.operationmanage.GetAppInfoParam;
import io.github.liuruinian.im.core.params.operationmanage.GetHistoryMsgParam;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public class DefaultOperationManageApi extends AbstractOperationManageApi {

    public DefaultOperationManageApi(ImServerProperties imServerProperties,
                                     UserSignService userSignService,
                                     ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService, eventPublisher);
    }

    @Override
    protected String handleAppInfo(GetAppInfoParam getAppInfoParam) {
        String getAppInfoApi = TencentCloudImApiConstant.OperationManage.GET_APP_INFO;

        String requestUrl = obtainRequestUrl(getAppInfoApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultOperationManageApi] -> getappinfo request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(getAppInfoParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultOperationManageApi] -> getappinfo request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultOperationManageApi] -> getappinfo response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleHistoryMsg(GetHistoryMsgParam getHistoryMsgParam) {
        String getHistoryApi = TencentCloudImApiConstant.OperationManage.GET_HISTORY;

        String requestUrl = obtainRequestUrl(getHistoryApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultOperationManageApi] -> get_history_msg request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(getHistoryMsgParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultOperationManageApi] -> get_history_msg request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultOperationManageApi] -> get_history_msg response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleIpList() {
        String getIpListApi = TencentCloudImApiConstant.OperationManage.GET_IP_LIST;

        String requestUrl = obtainRequestUrl(getIpListApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultOperationManageApi] -> get_ip_list request url : {}", requestUrl);
        }

        String requestBody = "{}";
        if (log.isInfoEnabled()) {
            log.info("[DefaultOperationManageApi] -> get_ip_list request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultOperationManageApi] -> get_ip_list response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
