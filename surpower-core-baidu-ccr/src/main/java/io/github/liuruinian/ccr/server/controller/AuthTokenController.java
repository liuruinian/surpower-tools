package io.github.liuruinian.ccr.server.controller;

import io.github.liuruinian.ccr.core.authtoken.AuthTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Token认证API")
public class AuthTokenController {

    private AuthTokenService authTokenService;

    @Autowired
    public void setAuthTokenService(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @ApiOperation("获取AuthToken")
    public ResponseEntity<String> getRefreshAuthToken() {
        try {
            String authToken = authTokenService.getAuthToken();
            return ResponseEntity.ok(authToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
