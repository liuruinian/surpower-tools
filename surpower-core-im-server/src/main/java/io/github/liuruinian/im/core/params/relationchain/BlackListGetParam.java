package io.github.liuruinian.im.core.params.relationchain;

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
 * @version 2022-02-10
 * <p>
 *     拉取黑名单参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("拉取黑名单参数")
public class BlackListGetParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "需要拉取该 UserID 的黑名单", required = true)
    private String     fromAccount;

    @JsonProperty(value = "StartIndex")
    @JSONField(name = "StartIndex")
    @ApiModelProperty(name = "startIndex", value = "拉取的起始位置", required = true)
    private Integer    startIndex;

    @JsonProperty(value = "MaxLimited")
    @JSONField(name = "MaxLimited")
    @ApiModelProperty(name = "maxLimited", value = "每页最多拉取的黑名单数", required = true)
    private Integer    maxLimited;

    @JsonProperty(value = "LastSequence")
    @JSONField(name = "LastSequence")
    @ApiModelProperty(name = "lastSequence", value = "上一次拉黑名单时后台返回给客户端的 Seq，初次拉取时为0", required = true)
    private Integer    lastSequence;
}
