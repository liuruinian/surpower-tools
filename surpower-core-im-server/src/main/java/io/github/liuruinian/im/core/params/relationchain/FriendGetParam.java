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
 * <p>
 *     拉取好友参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("拉取好友参数")
public class FriendGetParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "指定要拉取好友数据的用户的 UserID", required = true)
    private String fromAccount;

    @JsonProperty(value = "StartIndex")
    @JSONField(name = "StartIndex")
    @ApiModelProperty(name = "startIndex", value = "分页的起始位置", required = true)
    private Integer startIndex;

    @JsonProperty(value = "StandardSequence")
    @JSONField(name = "StandardSequence")
    @ApiModelProperty(name = "standardSequence", value = "上次拉好友数据时返回的 StandardSequence，如果 StandardSequence 字段的值与后台一致，后台不会返回标配好友数据")
    private Integer standardSequence;

    @JsonProperty(value = "CustomSequence")
    @JSONField(name = "CustomSequence")
    @ApiModelProperty(name = "customSequence", value = "上次拉好友数据时返回的 CustomSequence，如果 CustomSequence 字段的值与后台一致，后台不会返回自定义好友数据")
    private Integer customSequence;
}
