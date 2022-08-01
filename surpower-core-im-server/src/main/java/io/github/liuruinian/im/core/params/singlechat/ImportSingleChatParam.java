package io.github.liuruinian.im.core.params.singlechat;

import com.alibaba.fastjson.JSONArray;
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
 *     导入单聊消息请求体
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("批量发单聊请求体")
public class ImportSingleChatParam implements Serializable {

    @JsonProperty(value = "SyncFromOldSystem")
    @JSONField(name = "SyncFromOldSystem")
    @ApiModelProperty(name = "syncFromOldSystem", value =   "该字段只能填1或2，其他值是非法值\n" +
                                                            "1表示实时消息导入，消息计入未读计数\n" +
                                                            "2表示历史消息导入，消息不计入未读", required = true)
    private Integer     syncFromOldSystem;

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "消息发送方 UserID（用于指定发送消息方帐号）", required = true)
    private String      fromAccount;

    @JsonProperty(value = "To_Account")
    @JSONField(name = "To_Account")
    @ApiModelProperty(name = "toAccount", value = "消息接收方 UserID", required = true)
    private String      toAccount;

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
    @ApiModelProperty(name = "msgTimeStamp", value = "消息时间戳，UNIX 时间戳（单位：秒）", required = true)
    private Integer     msgTimeStamp;

    @JsonProperty(value = "MsgBody")
    @JSONField(name = "MsgBody")
    @ApiModelProperty(name = "msgBody", value = "消息内容; 具体格式详见：https://cloud.tencent.com/document/product/269/2720", required = true)
    private JSONArray msgBody;

    @JsonProperty(value = "CloudCustomData")
    @JSONField(name = "CloudCustomData")
    @ApiModelProperty(name = "cloudCustomData", value = "消息自定义数据（云端保存，会发送到对端，程序卸载重装后还能拉取到）")
    private String      cloudCustomData;
}
