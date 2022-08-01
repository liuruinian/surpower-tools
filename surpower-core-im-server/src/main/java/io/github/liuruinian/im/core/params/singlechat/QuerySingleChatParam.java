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
 *     查询单聊消息请求体
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("查询单聊消息请求体")
public class QuerySingleChatParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "消息发送方 UserID（用于指定发送消息方帐号）", required = true)
    private String      fromAccount;

    @JsonProperty(value = "To_Account")
    @JSONField(name = "To_Account")
    @ApiModelProperty(name = "toAccount", value = "消息接收方 UserID", required = true)
    private String      toAccount;

    @JsonProperty(value = "MaxCnt")
    @JSONField(name = "MaxCnt")
    @ApiModelProperty(name = "maxCnt", value = "请求的消息条数", required = true)
    private Integer     maxCnt;

    @JsonProperty(value = "MinTime")
    @JSONField(name = "MinTime")
    @ApiModelProperty(name = "minTime", value = "请求的消息时间范围的最小值", required = true)
    private Integer     minTime;

    @JsonProperty(value = "MaxTime")
    @JSONField(name = "MaxTime")
    @ApiModelProperty(name = "maxTime", value = "请求的消息时间范围的最大值", required = true)
    private Integer     maxTime;

    @JsonProperty(value = "LastMsgKey")
    @JSONField(name = "LastMsgKey")
    @ApiModelProperty(name = "lastMsgKey", value = "上一次拉取到的最后一条消息的 MsgKey，续拉时需要填该字段.")
    private String      lastMsgKey;
}
