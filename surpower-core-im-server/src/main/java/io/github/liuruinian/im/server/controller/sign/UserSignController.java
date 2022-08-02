package io.github.liuruinian.im.server.controller.sign;

import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户签名API")
public class UserSignController {

    private UserSignService userSignService;

    @Autowired
    public void setUserSignService(UserSignService userSignService) {
        this.userSignService = userSignService;
    }

    @ApiOperation("生成用户签名")
    @ApiImplicitParam(name = "user_id", value = "当前登录用户ID", paramType = "path", dataType = "String")
    public ResponseEntity<JSONObject> generateUserSign(@PathVariable(name = "user_id") String userId) {
        JSONObject result = new JSONObject();
        result.put("code", HttpStatus.OK.value());
        result.put("msg", "OK");
        result.put("data", userSignService.obtainUserSign(userId));

        return ResponseEntity.ok(result);
    }
}
