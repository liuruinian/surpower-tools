package io.github.liuruinian.pp.controller;

import com.aliyuncs.dyplsapi.model.v20170525.BindAxnResponse;
import com.google.common.base.Preconditions;
import io.github.liuruinian.pp.domain.AxnBindParam;
import io.github.liuruinian.pp.handler.bind.BindHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "号码绑定API")
public class BindController {

    private BindHandler bindHandler;

    @Autowired
    public void setBindHandler(BindHandler bindHandler) {
        this.bindHandler = bindHandler;
    }

    @ApiOperation("AXN绑定接口")
    public ResponseEntity<BindAxnResponse> bindAxn(@RequestBody AxnBindParam param) {
        Preconditions.checkNotNull(param, "AXN bind request params not allow empty!");

        return ResponseEntity.ok(bindHandler.bindAxn(param));
    }
}
