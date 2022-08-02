package io.github.liuruinian.im.server.api.portrait;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.im.core.constant.TencentCloudImApiConstant;
import io.github.liuruinian.im.core.params.portrait.PortraitGetParam;
import io.github.liuruinian.im.core.params.portrait.PortraitSetParam;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public class DefaultPortraitManageApi extends AbstractPortraitManageApi {

    public DefaultPortraitManageApi(ImServerProperties imServerProperties,
                                    UserSignService userSignService,
                                    ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService, eventPublisher);
    }

    @Override
    protected String handlePortraitSet(PortraitSetParam portraitSetParam) {
        String portraitSetApi = TencentCloudImApiConstant.PortraitManage.PORTRAIT_SET;

        String requestUrl = obtainRequestUrl(portraitSetApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultPortraitManageApi] -> portrait_set request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(portraitSetParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultPortraitManageApi] -> portrait_set request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultPortraitManageApi] -> portrait_set response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String handlePortraitGet(PortraitGetParam portraitGetParam) {
        String portraitGetApi = TencentCloudImApiConstant.PortraitManage.PORTRAIT_GET;

        String requestUrl = obtainRequestUrl(portraitGetApi);
        if (log.isInfoEnabled()) {
            log.info("[DefaultPortraitManageApi] -> portrait_get request url : {}", requestUrl);
        }

        String requestBody = JSONObject.toJSONString(portraitGetParam);
        if (log.isInfoEnabled()) {
            log.info("[DefaultPortraitManageApi] -> portrait_get request body : {}\n", requestBody);
        }

        try {
            String responseBody = HttpUtil.post(requestUrl, requestBody);

            if (log.isInfoEnabled()) {
                log.info("[DefaultPortraitManageApi] -> portrait_get response json body : \n{}", responseBody);
            }

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
