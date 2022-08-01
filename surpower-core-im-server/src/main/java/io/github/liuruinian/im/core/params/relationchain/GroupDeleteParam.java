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
 * @version 2022-02-14
 * <p>
 *     删除分组参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("删除分组参数")
public class GroupDeleteParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "需要删除该 UserID 的分组", required = true)
    private String fromAccount;

    @JsonProperty(value = "GroupName")
    @JSONField(name = "GroupName")
    @ApiModelProperty(name = "groupName", value = "要删除的分组列表", required = true)
    private String[] groupName;
}
