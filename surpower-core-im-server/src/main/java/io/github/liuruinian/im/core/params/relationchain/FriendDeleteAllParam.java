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
 *     删除所有好友参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("删除所有好友参数")
public class FriendDeleteAllParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "指定要清除好友数据的用户的 UserID", required = true)
    private String fromAccount;

    @JsonProperty(value = "DeleteType")
    @JSONField(name = "DeleteType")
    @ApiModelProperty(name = "deleteType", value = "删除模式，默认删除单向好友，详情可参见: https://cloud.tencent.com/document/product/269/1501#.E5.88.A0.E9.99.A4.E5.A5.BD.E5.8F.8B")
    private String deleteType;
}
