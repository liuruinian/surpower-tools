package io.github.liuruinian.im.core.params.globalmute;

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
 * 设置全局禁言参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("设置全局禁言参数")
public class SetNoSpeakingParam implements Serializable {

    @JsonProperty(value = "Set_Account")
    @JSONField(name = "Set_Account")
    @ApiModelProperty(name = "setAccount", value = "设置禁言配置的帐号", required = true)
    private String  setAccount;

    @JsonProperty(value = "C2CmsgNospeakingTime")
    @JSONField(name = "C2CmsgNospeakingTime")
    @ApiModelProperty(name = "c2cMsgNospeakingTime", value =    "单聊消息禁言时间，单位为秒，非负整数，最大值为4294967295\n" +
                                                                "0表示取消该帐号的单聊消息禁言\n" +
                                                                "4294967295表示该帐号被设置永久禁言\n" +
                                                                "其它值表示该帐号具体的禁言时间")
    private Integer c2cMsgNospeakingTime;

    @JsonProperty(value = "GroupmsgNospeakingTime")
    @JSONField(name = "GroupmsgNospeakingTime")
    @ApiModelProperty(name = "groupmsgNospeakingTime", value =  "群组消息禁言时间，单位为秒，非负整数，最大值为4294967295\n" +
                                                                "0表示取消该帐号的群组消息禁言\n" +
                                                                "4294967295表示该帐号被设置永久禁言\n" +
                                                                "其它值表示该帐号的具体禁言时间")
    private Integer groupmsgNospeakingTime;
}
