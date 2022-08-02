package io.github.liuruinian.im.server.callback.group;

import com.alibaba.fastjson.JSONObject;

/**
 * 群解散之后的回调
 */
public interface AfterGroupDestroyedCallbackProvider {
    void handleCallback(String sdkAppid, String callbackCommand, String clientIP, String optPlatform, JSONObject requestBody);
}
