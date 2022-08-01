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
 * 检验好友参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("检验好友参数")
public class FriendCheckParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "需要校验该 UserID 的好友", required = true)
    private String      fromAccount;

    @JsonProperty(value = "To_Account")
    @JSONField(name = "To_Account")
    @ApiModelProperty(name = "toAccount", value = "请求校验的好友的 UserID 列表，单次请求的 To_Account 数不得超过1000", required = true)
    private String[]    toAccount;

    @JsonProperty(value = "CheckType")
    @JSONField(name = "CheckType")
    @ApiModelProperty(name = "checkType", value = "校验模式，详情可参见: https://cloud.tencent.com/document/product/269/1501#.E6.A0.A1.E9.AA.8C.E5.A5.BD.E5.8F.8B", required = true)
    private String      checkType;
}
