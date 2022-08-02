package io.github.liuruinian.im.server.callback.group;

import com.alibaba.fastjson.JSONObject;

/**
 * 群成员离开之后回调
 */
public interface AfterMemberExitCallbackProvider {
    void handleCallback(String sdkAppid, String callbackCommand, String clientIP, String optPlatform, JSONObject requestBody);
}
