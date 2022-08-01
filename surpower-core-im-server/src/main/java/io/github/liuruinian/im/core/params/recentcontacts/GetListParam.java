package io.github.liuruinian.im.core.params.recentcontacts;

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
 * @version 2022-02-14
 * <p>
 *     拉取会话列表参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("拉取会话列表参数")
public class GetListParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "填 UserID，请求拉取该用户的会话列表", required = true)
    private String  fromAccount;

    @JsonProperty(value = "TimeStamp")
    @JSONField(name = "TimeStamp")
    @ApiModelProperty(name = "timeStamp", value = "普通会话的起始时间，第一页填 0", required = true)
    private Integer timeStamp;

    @JsonProperty(value = "StartIndex")
    @JSONField(name = "StartIndex")
    @ApiModelProperty(name = "startIndex", value = "普通会话的起始位置，第一页填 0", required = true)
    private Integer startIndex;

    @JsonProperty(value = "TopTimeStamp")
    @JSONField(name = "TopTimeStamp")
    @ApiModelProperty(name = "topTimeStamp", value = "置顶会话的起始时间，第一页填 0", required = true)
    private Integer topTimeStamp;

    @JsonProperty(value = "TopStartIndex")
    @JSONField(name = "TopStartIndex")
    @ApiModelProperty(name = "topStartIndex", value = "置顶会话的起始位置，第一页填 0", required = true)
    private Integer topStartIndex;

    @JsonProperty(value = "AssistFlags")
    @JSONField(name = "AssistFlags")
    @ApiModelProperty(name = "assistFlags", value = "会话辅助标志位:\n" +
                                                    "bit 0 - 是否支持置顶会话\n" +
                                                    "bit 1 - 是否返回空会话\n" +
                                                    "bit 2 - 是否支持置顶会话分页", required = true)
    private Integer assistFlags;
}
