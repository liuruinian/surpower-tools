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
 * @author hk417
 * @version Created in 2022/2/11 17:30
 * <p>
 *      删除好友参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("删除好友参数")
public class FriendDeleteParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "需要删除该 UserID 的好友")
    private String      fromAccount;

    @JsonProperty(value = "To_Account")
    @JSONField(name = "To_Account")
    @ApiModelProperty(name = "toAccount", value = "待删除的好友的 UserID 列表，单次请求的 To_Account 数不得超过1000")
    private String[]    toAccount;

    @JsonProperty(value = "DeleteType")
    @JSONField(name = "DeleteType")
    @ApiModelProperty(name = "deleteType", value = "删除模式，详情可参见：https://cloud.tencent.com/document/product/269/1501#.E5.88.A0.E9.99.A4.E5.A5.BD.E5.8F.8B")
    private String      deleteType;
}
