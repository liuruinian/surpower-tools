package io.github.liuruinian.im.core.params.operationmanage;

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
 * 下载最近消息记录参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("下载最近消息记录参数")
public class GetHistoryMsgParam implements Serializable {

    @JsonProperty(value = "ChatType")
    @JSONField(name = "ChatType")
    @ApiModelProperty(name = "chatType", value = "消息类型，C2C 表示单发消息 Group 表示群组消息", required = true)
    private String chatType;

    @JsonProperty(value = "MsgTime")
    @JSONField(name = "MsgTime")
    @ApiModelProperty(name = "", value =    "需要下载的消息记录的时间段，2015120121表示获取2015年12月1日21:00 - 21:59的消息的下载地址。\n" +
                                            "该字段需精确到小时。每次请求只能获取某天某小时的所有单发或群组消息记录", required = true)
    private String msgTime;
}
