package io.github.liuruinian.im.server.callback.friendadd;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.im.core.params.portrait.PortraitGetParam;
import io.github.liuruinian.im.core.params.singlechat.SingleIssueSingleChatParam;
import io.github.liuruinian.im.core.restapi.PortraitManageApi;
import io.github.liuruinian.im.core.restapi.SingleChatApi;
import io.github.liuruinian.im.core.util.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author hk417
 * @version Created in 2022/4/13 17:13
 * <p>
 *      DefaultFriendAddCallbackProvider
 * </p>
 */
@Component
public class DefaultFriendAddCallbackProvider implements FriendAddCallbackProvider {

    private SingleChatApi singleChatApi;

    private PortraitManageApi portraitManageApi;

    @Autowired
    @Lazy
    public void setSingleChatApi(SingleChatApi singleChatApi) {
        this.singleChatApi = singleChatApi;
    }

    @Autowired
    @Lazy
    public void setPortraitManageApi(PortraitManageApi portraitManageApi) {
        this.portraitManageApi = portraitManageApi;
    }

    @Override
    public void handleCallback(String sdkAppid, String callbackCommand, String clientIP, String optPlatform, JSONObject requestBody) {

        JSONArray pairList = requestBody.getJSONArray("PairList");
        String initiator_account = pairList.getJSONObject(0).getString("Initiator_Account");
        String to_account = "";

        for (int i = 0; i < pairList.size(); i++) {
            JSONObject pair = pairList.getJSONObject(i);
            if (pair.getString("Initiator_Account").equals(initiator_account) && !pair.getString("To_Account").equals(initiator_account)) {
                to_account = pair.getString("To_Account");
                break;
            }
        }


        SingleIssueSingleChatParam from = SingleIssueSingleChatParam.builder()
                .toAccount(to_account)
                .fromAccount(initiator_account)
                .msgRandom(ApiUtils.msgRandom())
                .msgBody(fromMsgBody(to_account))
                .build();

        SingleIssueSingleChatParam to = SingleIssueSingleChatParam.builder()
                .toAccount(initiator_account)
                .fromAccount(to_account)
                .msgRandom(ApiUtils.msgRandom())
                .msgBody(toMsgBody(initiator_account))
                .build();

        singleChatApi.sendMsg(from);
        singleChatApi.sendMsg(to);

    }

    public JSONArray fromMsgBody(String fromAccount) {
        PortraitGetParam portraitGetParam = PortraitGetParam.builder()
                .toAccount(new String[]{fromAccount})
                .tagList(new String[]{"Tag_Profile_IM_Nick"})
                .build();
        String response = portraitManageApi.portrait_get(portraitGetParam);
        JSONObject result = JSONObject.parseObject(response);
        JSONArray userProfileItemArr = result.getJSONArray("UserProfileItem");
        JSONObject userProfileItem = userProfileItemArr.getJSONObject(0);
        JSONArray profileItemArr = userProfileItem.getJSONArray("ProfileItem");
        JSONObject profileItem = profileItemArr.getJSONObject(0);
        String nickname = profileItem.getString("Value");

        JSONArray msgBodyArray = new JSONArray();
        JSONObject msgBody = new JSONObject();
        msgBody.put("MsgType", "TIMTextElem");
        JSONObject msgContent = new JSONObject();
        msgContent.put("Text", "您与" + nickname + "已经成为好友，现在可以开始聊天了!");
        msgBody.put("MsgContent", msgContent);
        msgBodyArray.add(msgBody);

        return msgBodyArray;
    }

    public JSONArray toMsgBody(String toAccount) {
        PortraitGetParam portraitGetParam = PortraitGetParam.builder()
                .toAccount(new String[]{toAccount})
                .tagList(new String[]{"Tag_Profile_IM_Nick"})
                .build();
        String response = portraitManageApi.portrait_get(portraitGetParam);
        JSONObject result = JSONObject.parseObject(response);
        JSONArray userProfileItemArr = result.getJSONArray("UserProfileItem");
        JSONObject userProfileItem = userProfileItemArr.getJSONObject(0);
        JSONArray profileItemArr = userProfileItem.getJSONArray("ProfileItem");
        JSONObject profileItem = profileItemArr.getJSONObject(0);
        String nickname = profileItem.getString("Value");

        JSONArray msgBodyArray = new JSONArray();
        JSONObject msgBody = new JSONObject();
        msgBody.put("MsgType", "TIMTextElem");
        JSONObject msgContent = new JSONObject();
        msgContent.put("Text", "您与" + nickname + "已经成为好友，现在可以开始聊天了!");
        msgBody.put("MsgContent", msgContent);
        msgBodyArray.add(msgBody);

        return msgBodyArray;
    }
}
