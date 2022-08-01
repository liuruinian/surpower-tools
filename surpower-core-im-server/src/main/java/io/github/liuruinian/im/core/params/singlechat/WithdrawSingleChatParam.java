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
 *     撤回单聊消息请求体
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("撤回单聊消息请求体")
public class WithdrawSingleChatParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "消息发送方 UserID（用于指定发送消息方帐号）", required = true)
    private String      fromAccount;

    @JsonProperty(value = "To_Account")
    @JSONField(name = "To_Account")
    @ApiModelProperty(name = "toAccount", value = "消息接收方 UserID", required = true)
    private String      toAccount;

    @JsonProperty(value = "MsgKey")
    @JSONField(name = "MsgKey")
    @ApiModelProperty(name = "msgKey", value = "待撤回消息的唯一标识", required = true)
    private String      msgKey;
}
