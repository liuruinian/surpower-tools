package io.github.liuruinian.im.core.params.singlechat;

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
 * @version 2022-02-09
 * <p>
 *     设置单聊消息已读请求体
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("设置单聊消息已读请求体")
public class ReadSingleChatParam implements Serializable {

    @JsonProperty(value = "Report_Account")
    @JSONField(name = "Report_Account")
    @ApiModelProperty(name = "reportAccount", value = "进行消息已读的用户 UserId", required = true)
    private String reportAccount;

    @JsonProperty(value = "Peer_Account")
    @JSONField(name = "Peer_Account")
    @ApiModelProperty(name = "peerAccount", value = "进行消息已读的单聊会话的另一方用户 UserId", required = true)
    private String peerAccount;

    @JsonProperty(value = "MsgReadTime")
    @JSONField(name = "MsgReadTime")
    @ApiModelProperty(name = "msgReadTime", value = "时间戳（秒），该时间戳之前的消息全部已读。若不填，则取当前时间戳")
    private String msgReadTime;
}
