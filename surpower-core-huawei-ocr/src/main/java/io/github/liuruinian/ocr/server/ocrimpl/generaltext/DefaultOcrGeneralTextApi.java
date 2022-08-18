package io.github.liuruinian.ocr.server.ocrimpl.generaltext;

import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.ocr.core.authtoken.AuthTokenService;
import io.github.liuruinian.ocr.core.constant.HuaweiCloudOcrApiConstant;
import io.github.liuruinian.ocr.core.param.OcrGeneralTextParam;
import io.github.liuruinian.ocr.core.util.HuaweiOcrApiUtil;
import io.github.liuruinian.ocr.server.properties.HuaweiOcrProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultOcrGeneralTextApi extends AbstractOcrGeneralTextApi {

    private HuaweiOcrProperties properties;

    private AuthTokenService authTokenService;

    @Autowired
    public void setAuthTokenService(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @Autowired
    public void setProperties(HuaweiOcrProperties properties) {
        this.properties = properties;
    }

    @Override
    protected String handleGeneralTextOcr(OcrGeneralTextParam param) {
        String requestUri = HuaweiOcrApiUtil.obtainRequestUri(HuaweiCloudOcrApiConstant.OCR_GENERAL_TEXT_API, properties);

        if (log.isInfoEnabled()) {
            log.info("[DefaultOcrGeneralTextApi] -> general text ocr request url : {}", requestUri);
        }

        try {
            String requestBody = JSONObject.toJSONString(param, true);
            log.info("[DefaultOcrGeneralTextApi] -> request body: \n{}", requestBody);

            HttpResponse response = HttpUtil.createPost(requestUri)
                    .header(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header("X-Auth-Token", authTokenService.obtainRefreshableAuthToken())
                    .body(requestBody)
                    .execute();

            return response.body();
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[DefaultOcrGeneralTextApi] -> general text ocr error!", e);
            }
            throw new RuntimeException(e);
        }
    }
}