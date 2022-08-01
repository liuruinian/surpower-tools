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
 *     添加分组参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("添加分组参数")
public class GroupAddParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "需要为该 UserID 添加新分组", required = true)
    private String     fromAccount;

    @JsonProperty(value = "GroupName")
    @JSONField(name = "GroupName")
    @ApiModelProperty(name = "groupName", value = "新增分组列表", required = true)
    private String[]    groupName;

    @JsonProperty(value = "To_Account")
    @JSONField(name = "To_Account")
    @ApiModelProperty(name = "toAccount", value = "需要加入新增分组的好友的 UserID 列表")
    private String[]    toAccount;
}
