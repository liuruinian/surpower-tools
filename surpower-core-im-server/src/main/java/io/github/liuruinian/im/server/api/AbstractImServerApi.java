package io.github.liuruinian.im.server.api;

import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.core.util.ApiUtils;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.Data;

@Data
public abstract class AbstractImServerApi {

    private ImServerProperties imServerProperties;

    private UserSignService userSignService;

    public AbstractImServerApi(ImServerProperties imServerProperties,
                               UserSignService userSignService) {
        this.imServerProperties = imServerProperties;
        this.userSignService = userSignService;
    }

    /**
     * 获取IM服务请求地址
     */
    public String obtainRequestUrl(String imServiceApi) {
        Long sdkAppId = imServerProperties.getSdkAppId();
        final String appManager = imServerProperties.getAppManager();
        final String userSign = userSignService.obtainUserSign(appManager);

        return ApiUtils.obtainRequestUrl(imServiceApi, sdkAppId, appManager, userSign, ApiUtils.randomRequestId());
    }
}
