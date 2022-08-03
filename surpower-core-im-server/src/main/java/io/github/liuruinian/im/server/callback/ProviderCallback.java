package io.github.liuruinian.im.server.callback;

import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.im.server.callback.friendadd.FriendAddCallbackProvider;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author hk417
 * @version Created in 2022/4/13 17:09
 * <p>
 *      ProviderCallback
 * </p>
 */
@Component
public class ProviderCallback implements CallbackHandler {

    /** 加完好友后的回调命令 */
    private static final String FRIEND_ADD_CALLBACK_COMMAND = "Sns.CallbackFriendAdd";

    /** 用户离线回调命令 */
    private static final String USER_STATE_CHANGE = "State.StateChange";

    /** 群组创建成功之后的回调命令 */
    private static final String GROUP_AFTER_CREATED_COMMAND = "Group.CallbackAfterCreateGroup";

    /** 新成员入群之后的回调命令  */
    private static final String GROUP_AFTER_NEW_MEMBER_JOIN = "Group.CallbackAfterNewMemberJoin";

    /** 群成员离开之后的回调命令 */
    private static final String GROUP_AFTER_MEMBER_EXIST = "Group.CallbackAfterMemberExit";

    @Autowired
    private FriendAddCallbackProvider friendAddCallbackProvider;

    @Autowired
    @Lazy
    private ImServerProperties imServerProperties;

    @Override
    public void handleCallback(String sdkAppid, String callbackCommand, String clientIP, String optPlatform, JSONObject requestBody) {
        checkSdkidVaild(sdkAppid);

        if (FRIEND_ADD_CALLBACK_COMMAND.equals(callbackCommand)) {
            friendAddCallbackProvider.handleCallback(sdkAppid, callbackCommand, clientIP, optPlatform, requestBody);
        }
    }

    private void checkSdkidVaild(String sdkAppid) {
        Long candidate = imServerProperties.getSdkAppId();
        if (!sdkAppid.equals(String.valueOf(candidate))) {
            throw new RuntimeException("SDKID is invalid!");
        }
    }
}
