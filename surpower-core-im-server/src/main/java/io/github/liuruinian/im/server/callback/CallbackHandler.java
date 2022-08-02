package io.github.liuruinian.im.server.callback;

import com.alibaba.fastjson.JSONObject;

public interface CallbackHandler {

    void handleCallback(String sdkAppid, String callbackCommand, String clientIP, String optPlatform, JSONObject requestBody);
}
