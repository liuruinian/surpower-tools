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
public class BlackListCheckParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "需要拉取该 UserID 的黑名单", required = true)
    private String     fromAccount;

    @JsonProperty(value = "To_Account")
    @JSONField(name = "To_Account")
    @ApiModelProperty(name = "toAccount", value = "待校验的黑名单的 UserID 列表，单次请求的 To_Account 数不得超过1000", required = true)
    private String[]    toAccount;

    @JsonProperty(value = "CheckType")
    @JSONField(name = "CheckType")
    @ApiModelProperty(name = "checkType", value = "校验模式，详情可参见: https://cloud.tencent.com/document/product/269/1501#.E6.A0.A1.E9.AA.8C.E9.BB.91.E5.90.8D.E5.8D.95", required = true)
    private String    checkType;
}
