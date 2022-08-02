package io.github.liuruinian.im.server.api.singlechat;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.im.core.constant.TencentCloudImApiConstant;
import io.github.liuruinian.im.core.params.singlechat.*;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public class DefaultSingleChatApi extends AbstractSingleChatApi {

    public DefaultSingleChatApi(ImServerProperties imServerProperties,
                                UserSignService userSignService,
                                ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService, eventPublisher);
    }

    @Override
    protected String handleSendMsg(SingleIssueSingleChatParam singleIssueSingleChatParam) {
        String sendMsgApi = TencentCloudImApiConstant.SingleChatManage.SEND_MSG;

        String requestUrl = obtainRequestUrl(sendMsgApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultSingleChatApi] -> send msg request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(singleIssueSingleChatParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultSingleChatApi] -> send msg request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultSingleChatApi] -> send msg response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleBatchSendMsg(BatchSingleChatParam batchSingleChatParam) {
        String batchSendMsgApi = TencentCloudImApiConstant.SingleChatManage.BATCH_SEND_MSG;

        String requestUrl = obtainRequestUrl(batchSendMsgApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultSingleChatApi] -> batch send msg request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(batchSingleChatParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultSingleChatApi] -> batch send msg request body : {}\n", JSONUtil.toJsonPrettyStr(batchSingleChatParam));
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultSingleChatApi] -> batch send msg response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleImportMsg(ImportSingleChatParam importSingleChatParam) {
        String importMsgApi = TencentCloudImApiConstant.SingleChatManage.IMPORT_MSG;

        String requestUrl = obtainRequestUrl(importMsgApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultSingleChatApi] -> import msg request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(importSingleChatParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultSingleChatApi] -> import msg request body : {}\n", JSONUtil.toJsonPrettyStr(importSingleChatParam));
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultSingleChatApi] -> import msg response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleAdminGetRoamMsg(QuerySingleChatParam querySingleChatParam) {
        String adminGetRoamMsgApi = TencentCloudImApiConstant.SingleChatManage.ADMIN_GET_ROAM_MSG;

        String requestUrl = obtainRequestUrl(adminGetRoamMsgApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultSingleChatApi] -> admin getroam msg request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(querySingleChatParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultSingleChatApi] -> admin getroam msg request body : {}\n", JSONUtil.toJsonPrettyStr(querySingleChatParam));
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultSingleChatApi] -> admin getroam msg response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleAdminMsgWithdraw(WithdrawSingleChatParam withdrawSingleChatParam) {
        String adminMsgWithDrawApi = TencentCloudImApiConstant.SingleChatManage.ADMIN_MSG_WITH_DRAW;

        String requestUrl = obtainRequestUrl(adminMsgWithDrawApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultSingleChatApi] -> admin msg withdraw request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(withdrawSingleChatParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultSingleChatApi] -> admin msg withdraw request body : {}\n", JSONUtil.toJsonPrettyStr(withdrawSingleChatParam));
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultSingleChatApi] -> admin msg withdraw response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleAdminSetMsgRead(ReadSingleChatParam readSingleChatParam) {
        String adminSetMsgReadApi = TencentCloudImApiConstant.SingleChatManage.ADMIN_SET_MSG_READ;

        String requestUrl = obtainRequestUrl(adminSetMsgReadApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultSingleChatApi] -> admin_set_msg_read request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(readSingleChatParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultSingleChatApi] -> admin_set_msg_read request body : {}\n", JSONUtil.toJsonPrettyStr(readSingleChatParam));
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultSingleChatApi] -> admin_set_msg_read response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleC2CUnreadMsgNum(C2CUnreadMsgNumParam c2CUnreadMsgNumParam) {
        String getC2cUnreadMsgNumApi = TencentCloudImApiConstant.SingleChatManage.GET_C2C_UNREAD_MSG_NUM;

        String requestUrl = obtainRequestUrl(getC2cUnreadMsgNumApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultSingleChatApi] -> get_c2c_unread_msg_num request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(c2CUnreadMsgNumParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultSingleChatApi] -> get_c2c_unread_msg_num request body : {}\n", JSONUtil.toJsonPrettyStr(c2CUnreadMsgNumParam));
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultSingleChatApi] -> get_c2c_unread_msg_num response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
