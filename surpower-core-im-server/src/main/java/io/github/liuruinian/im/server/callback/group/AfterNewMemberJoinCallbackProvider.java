package io.github.liuruinian.im.server.callback.group;

import com.alibaba.fastjson.JSONObject;

/**
 * 新成员入群之后的回调
 */
public interface AfterNewMemberJoinCallbackProvider {
    void handleCallback(String sdkAppid, String callbackCommand, String clientIP, String optPlatform, JSONObject requestBody);
}
