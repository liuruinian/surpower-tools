package io.github.liuruinian.fanco.server.controller.auth;

import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.fanco.core.token.AuthTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@Api(tags = "凡科互动ACCESS_TOKEN_API")
public class AuthTokenController {

    private AuthTokenService authTokenService;

    @Autowired
    public void setAuthTokenService(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @ApiOperation("获取ACCESS_TOKEN")
    public JSONObject oauthAccessToken() {
        String refreshToken = authTokenService.obtainRefreshToken();
        return JSONObject.parseObject(refreshToken);
    }

    @ApiOperation("获取授权码")
    public ResponseEntity<String> authorizationCode(HttpServletRequest request) {
        String authorizationCode = request.getParameter("code");

        return ResponseEntity.ok(StringUtils.isEmpty(authorizationCode) ? "" : authorizationCode);
    }
}
