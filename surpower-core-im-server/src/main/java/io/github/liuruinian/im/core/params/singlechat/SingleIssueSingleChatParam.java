package io.github.liuruinian.im.core.params.singlechat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liuruinian
 * @version 2022-02-08
 * <p>
 *     单发单聊请求体
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("单发单聊请求体")
public class SingleIssueSingleChatParam implements Serializable {

    @JsonProperty(value = "SyncOtherMachine")
    @JSONField(name = "SyncOtherMachine")
    @ApiModelProperty(name = "syncOtherMachine", value = "1：把消息同步到 From_Account 在线终端和漫游上；\n" +
                                                         "2：消息不同步至 From_Account；\n" +
                                                         "若不填写默认情况下会将消息存 From_Account 漫游")
    private Integer     syncOtherMachine;

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "消息发送方 UserID（用于指定发送消息方帐号）")
    private String      fromAccount;

    @JsonProperty(value = "To_Account")
    @JSONField(name = "To_Account")
    @ApiModelProperty(name = "toAccount", value = "消息接收方 UserID", required = true)
    private String      toAccount;

    @JsonProperty(value = "MsgLifeTime")
    @JSONField(name = "MsgLifeTime")
    @ApiModelProperty(name = "msgLifeTime", value = "消息离线保存时长（单位：秒），最长为7天（604800秒）\n" +
                                                    "1.若设置该字段为0，则消息只发在线用户，不保存离线\n" +
                                                    "2.若设置该字段超过7天（604800秒），仍只保存7天\n" +
                                                    "3.若不设置该字段，则默认保存7天")
    private Integer     msgLifeTime;

    @JsonProperty(value = "MsgSeq")
    @JSONField(name = "MsgSeq")
    @ApiModelProperty(name = "msgSeq", value = "消息序列号，后台会根据该字段去重及进行同秒内消息的排序，详细规则请看本接口的功能说明。若不填该字段，则由后台填入随机数")
    private Integer     msgSeq;

    @JsonProperty(value = "MsgRandom")
    @JSONField(name = "MsgRandom")
    @ApiModelProperty(name = "msgRandom", value = "消息随机数，后台用于同一秒内的消息去重。请确保该字段填的是随机数", required = true)
    private Integer     msgRandom;

    @JsonProperty(value = "MsgTimeStamp")
    @JSONField(name = "MsgTimeStamp")
    @ApiModelProperty(name = "msgTimeStamp", value = "消息时间戳，UNIX 时间戳（单位：秒）")
    private Integer     msgTimeStamp;

    @JsonProperty(value = "ForbidCallbackControl")
    @JSONField(name = "ForbidCallbackControl")
    @ApiModelProperty(name = "forbidCallbackControl", value = "消息回调禁止开关，只对本条消息有效\n" +
                                                              "1.ForbidBeforeSendMsgCallback 表示禁止发消息前回调.\n" +
                                                              "2.ForbidAfterSendMsgCallback 表示禁止发消息后回调.")
    private String[]    forbidCallbackControl;

    @JsonProperty(value = "SendMsgControl")
    @JSONField(name = "SendMsgControl")
    @ApiModelProperty(name = "sendMsgControl", value = "消息发送控制选项，是一个 String 数组，只对本条消息有效.\n" +
                                                       "1.NoUnread 表示该条消息不计入未读数.\n" +
                                                       "2.NoLastMsg 表示该条消息不更新会话列表.\n" +
                                                       "3.WithMuteNotifications 表示该条消息的接收方对发送方设置的免打扰选项生效（默认不生效）")
    private String[]    sendMsgControl;

    @JsonProperty(value = "MsgBody")
    @JSONField(name = "MsgBody")
    @ApiModelProperty(name = "msgBody", value = "消息内容; 具体格式详见：https://cloud.tencent.com/document/product/269/2720", required = true)
    private JSONArray   msgBody;

    @JsonProperty(value = "CloudCustomData")
    @JSONField(name = "CloudCustomData")
    @ApiModelProperty(name = "cloudCustomData", value = "消息自定义数据（云端保存，会发送到对端，程序卸载重装后还能拉取到）")
    private String      cloudCustomData;

    @JsonProperty(value = "OfflinePushInfo")
    @JSONField(name = "OfflinePushInfo")
    @ApiModelProperty(name = "offlinePushInfo", value = "离线推送信息配置; 具体格式详见：https://cloud.tencent.com/document/product/269/2720")
    private JSONObject  offlinePushInfo;
}
