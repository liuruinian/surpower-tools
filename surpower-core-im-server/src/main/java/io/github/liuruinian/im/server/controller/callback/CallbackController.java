package io.github.liuruinian.im.server.controller.callback;

import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.im.server.callback.CallbackHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hk417
 * @version Created in 2022/4/13 16:24
 * <p>
 *      APP_IM后台
 * </p>
 */
@RestController
@Api(tags = "CALLBACK_API")
@Slf4j
public class CallbackController {

    private CallbackHandler callbackHandler;

    @Autowired
    public void setCallbackHandler(CallbackHandler callbackHandler) {
        this.callbackHandler = callbackHandler;
    }

    @ApiOperation("回调接口")
    public void imcallback(@RequestBody JSONObject requestBody) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        String sdkAppid = request.getParameter("SdkAppid");
        String callbackCommand = request.getParameter("CallbackCommand");
        String clientIP = request.getParameter("ClientIP");
        String optPlatform = request.getParameter("OptPlatform");

        log.info("[ImServerController::imcallback] -> SdkAppid: {}", sdkAppid);
        log.info("[ImServerController::imcallback] -> CallbackCommand: {}", callbackCommand);
        log.info("[ImServerController::imcallback] -> ClientIP: {}", clientIP);
        log.info("[ImServerController::imcallback] -> OptPlatform: {}", optPlatform);

        log.info("[ImServerController::imcallback] -> requestBody: \n{}", JSONObject.toJSONString(requestBody, true));

        callbackHandler.handleCallback(sdkAppid, callbackCommand, clientIP, optPlatform, requestBody);
    }
}
