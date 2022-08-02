package io.github.liuruinian.im.server.callback.statechange;

import com.alibaba.fastjson.JSONObject;

public interface StateChangeCallbackProvider {
    void handleCallback(String sdkAppid, String callbackCommand, String clientIP, String optPlatform, JSONObject requestBody);
}
