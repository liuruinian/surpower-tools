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
 *     拉取指定好友资料和数据参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("拉取指定好友资料和数据参数")
public class FriendGetListParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "指定要拉取好友数据的用户的 UserID", required = true)
    private String      fromAccount;

    @JsonProperty(value = "To_Account")
    @JSONField(name = "To_Account")
    @ApiModelProperty(name = "toAccount", value =   "好友的 UserID 列表\n" +
                                                    "建议每次请求的好友数不超过100，避免因数据量太大导致回包失败", required = true)
    private String[]    toAccount;

    @JsonProperty(value = "TagList")
    @JSONField(name = "TagList")
    @ApiModelProperty(name = "tagList", value = "指定要拉取的资料字段及好友字段")
    private String[]    tagList;
}
