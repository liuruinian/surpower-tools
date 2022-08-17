package io.github.liuruinian.ocr.server.controller;

import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.ocr.core.authtoken.AuthTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "Token认证API")
public class AuthTokenController {

    private AuthTokenService authTokenService;

    @Autowired
    public void setAuthTokenService(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @ApiOperation("获取AuthToken")
    public JSONObject getRefreshAuthToken() {
        try {
            String authToken = authTokenService.obtainRefreshableAuthToken();
            JSONObject result = new JSONObject();
            result.put("code", HttpStatus.OK.value());
            result.put("msg", "OK");
            result.put("data", authToken);
            return result;
        } catch (Exception e) {
            log.error("[AuthTokenController::getRefreshAuthToken] -> 获取AuthToken异常!", e);
            throw new RuntimeException(e);
        }
    }
}
