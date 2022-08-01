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
 *     添加好友请求参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("添加好友请求参数")
public class FriendAddParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "需要为该 UserID 添加好友", required = true)
    private String          fromAccount;

    @JsonProperty(value = "AddFriendItem")
    @JSONField(name = "AddFriendItem")
    @ApiModelProperty(name = "addFriendItem", value = "好友结构体对象", required = true)
    private AddFriendItem[] addFriendItem;

    @JsonProperty(value = "AddType")
    @JSONField(name = "AddType")
    @ApiModelProperty(name = "addType", value = "加好友方式（默认双向加好友方式）：\n" +
                                                "Add_Type_Single 表示单向加好友\n" +
                                                "Add_Type_Both 表示双向加好友")
    private String addType;

    @JsonProperty(value = "ForceAddFlags")
    @JSONField(name = "ForceAddFlags")
    @ApiModelProperty(name = "forceAddFlags", value = "管理员强制加好友标记：1表示强制加好友，0表示常规加好友方式")
    private Integer forceAddFlags;
}
