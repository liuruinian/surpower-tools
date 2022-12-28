package io.github.liuruinian.fanco.server.controller.activities;

import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.fanco.core.constants.FancoConstants;
import io.github.liuruinian.fanco.core.entity.ActivityListParam;
import io.github.liuruinian.fanco.core.entity.NoSenseActivity;
import io.github.liuruinian.fanco.server.properties.FancoProperties;
import io.github.liuruinian.fanco.server.restapi.activities.ActivitiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "获取活动API")
public class ActivitiesController {

    private ActivitiesService activitiesService;

    private FancoProperties properties;

    @Autowired
    public void setProperties(FancoProperties properties) {
        this.properties = properties;
    }

    @Autowired
    @Lazy
    public void setActivitiesService(ActivitiesService activitiesService) {
        this.activitiesService = activitiesService;
    }

    @ApiOperation("获取活动列表")
    public JSONObject list(@RequestBody ActivityListParam param) {
        return activitiesService.list(param);
    }

    @ApiOperation("获取活动详情")
    @ApiImplicitParam(name = "activity_id", value = "活动ID", dataType = "integer", paramType = "path")
    public JSONObject detail(@PathVariable("activity_id") Integer activityId) {
        return activitiesService.detail(activityId);
    }

    @ApiOperation("无感进入活动")
    public ResponseEntity<String> noSenseIntoActivity(@RequestBody NoSenseActivity body) {
        // 1.authorization_code
        String authCodeUrl = String.format(FancoConstants.OAUTH_AUTHORIZE_URL + "?client_id=%s&redirect_uri=%s&response_type=code&scope=all&auth_uid=%s",
                properties.getClientId(),
                properties.getAuthorizationCodeUrl(), body.getAuthUid());
        HttpResponse rec = HttpUtil.createGet(authCodeUrl)
                .execute();
        String authorizationCode = rec.body();

        // 2.partner_access_token
        String authTokenUrl = String.format(FancoConstants.OAUTH_TOKEN_URL + "?grant_type=authorization_code&redirect_uri=%s&code=%s",
                properties.getAuthorizationCodeUrl(),
                authorizationCode);
        HttpResponse ret = HttpUtil.createPost(authTokenUrl)
                .header(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(Header.AUTHORIZATION, "Basic " + encryptClientIdSecret())
                .execute();
        JSONObject pat = JSONObject.parseObject(ret.body());
        String partnerAccessToken = pat.getString("access_token");

        // 3.compose of activity url
        String activityUrl = body.getActivityUrl() + "&partner_access_token=" + partnerAccessToken;
        if (log.isInfoEnabled()) {
            log.info("activity url: {}", activityUrl);
        }

        return ResponseEntity.ok(activityUrl);
    }

    private String encryptClientIdSecret() {
        String clientId = properties.getClientId();
        String clientSecret = properties.getClientSecret();
        return Base64Utils.encodeToString((clientId + ":" + clientSecret).getBytes());
    }
}
