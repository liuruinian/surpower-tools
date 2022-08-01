package io.github.liuruinian.im.core.params.portrait;

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
 * 设置资料请求参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("设置资料请求参数")
public class PortraitSetParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "需要设置资料的 UserID", required = true)
    private String          fromAccount;

    @JsonProperty(value = "ProfileItem")
    @JSONField(name = "ProfileItem")
    @ApiModelProperty(name = "profileItem", value = "待设置的用户的资料对象数组，数组中每一个对象都包含了 Tag 和 Value", required = true)
    private PortraitItem[]  profileItem;
}
