package io.github.liuruinian.im.server.callback.friendadd;

import com.alibaba.fastjson.JSONObject;

public interface FriendAddCallbackProvider {
    void handleCallback(String sdkAppid, String callbackCommand, String clientIP, String optPlatform, JSONObject requestBody);
}
