package io.github.liuruinian.im.server.callback.group;

import com.alibaba.fastjson.JSONObject;

/**
 * @author liuruinian
 * @version 2022-04-24
 * <p>
 *     群组创建之后的回调
 * </p>
 */
public interface AfterGroupCreatedCallbackProvider {
    void handleCallback(String sdkAppid, String callbackCommand, String clientIP, String optPlatform, JSONObject requestBody);
}
