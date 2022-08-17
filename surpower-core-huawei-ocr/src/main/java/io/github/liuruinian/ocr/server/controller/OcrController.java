package io.github.liuruinian.ocr.server.controller;

import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.ocr.core.param.OcrIdCardParam;
import io.github.liuruinian.ocr.core.restapi.idcard.IdCardOcrApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "OCR识别API")
public class OcrController {

    private IdCardOcrApi idCardOcrApi;

    @Autowired
    public void setIdCardOcrApi(IdCardOcrApi idCardOcrApi) {
        this.idCardOcrApi = idCardOcrApi;
    }

    @ApiOperation("身份证识别")
    public JSONObject ocrIdCard(@RequestBody OcrIdCardParam param) {
        try {
            String responseBody = idCardOcrApi.ocrIdCard(param);
            return JSONObject.parseObject(responseBody);
        } catch (Exception e) {
            log.error("身份证识别异常!", e);
            throw new RuntimeException(e);
        }
    }
}
