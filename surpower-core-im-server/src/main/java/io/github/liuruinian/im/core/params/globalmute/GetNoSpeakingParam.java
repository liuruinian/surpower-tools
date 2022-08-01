package io.github.liuruinian.im.core.params.globalmute;

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
 * 查询全局禁言参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("查询全局禁言参数")
public class GetNoSpeakingParam implements Serializable {

    @JsonProperty(value = "Get_Account")
    @JSONField(name = "Get_Account")
    @ApiModelProperty(name = "getAccount", value = "查询禁言信息的帐号", required = true)
    private String  getAccount;
}
