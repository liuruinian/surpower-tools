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
 * 查询单聊未读消息计数参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("查询单聊未读消息计数参数")
public class C2CUnreadMsgNumParam implements Serializable {

    @JsonProperty(value = "To_Account")
    @JSONField(name = "To_Account")
    @ApiModelProperty(name = "toAccount", value = "待查询的用户 UserId", required = true)
    private String toAccount;

    @JsonProperty(value = "Peer_Account")
    @JSONField(name = "Peer_Account")
    @ApiModelProperty(name = "peerAccount", value = "待查询的单聊会话对端的用户 UserId\n" +
                                                    "若要查询单个会话的未读数，该字段必填\n" +
                                                    "该数组最大大小为10")
    private String[] peerAccount;
}
