package io.github.liuruinian.im.server.api.globalmute;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.im.core.constant.TencentCloudImApiConstant;
import io.github.liuruinian.im.core.params.globalmute.GetNoSpeakingParam;
import io.github.liuruinian.im.core.params.globalmute.SetNoSpeakingParam;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public class DefaultGlobalMuteManageApi extends AbstractGlobalMuteManageApi {

    public DefaultGlobalMuteManageApi(ImServerProperties imServerProperties,
                                      UserSignService userSignService,
                                      ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService, eventPublisher);
    }

    @Override
    protected String handleSetNoSpeaking(SetNoSpeakingParam setNoSpeakingParam) {
        String setNoSpeakingApi = TencentCloudImApiConstant.GlobalMuteManage.SET_NO_SPEAKING;

        String requestUrl = obtainRequestUrl(setNoSpeakingApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultGlobalMuteManageApi] -> setnospeaking request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(setNoSpeakingParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultGlobalMuteManageApi] -> setnospeaking request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultGlobalMuteManageApi] -> setnospeaking response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handleGetNoSpeaking(GetNoSpeakingParam getNoSpeakingParam) {
        String getNoSpeakingApi = TencentCloudImApiConstant.GlobalMuteManage.GET_NO_SPEAKING;

        String requestUrl = obtainRequestUrl(getNoSpeakingApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultGlobalMuteManageApi] -> getnospeaking request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(getNoSpeakingParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultGlobalMuteManageApi] -> getnospeaking request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultGlobalMuteManageApi] -> getnospeaking response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
