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
 * @version Created in 2022/2/11 15:51
 * <p>
 *      导入好友请求体参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("导入好友请求体参数")
public class FriendImportParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "需要为该 UserID 添加好友", required = true)
    private String              fromAccount;

    @JsonProperty(value = "AddFriendItem")
    @JSONField(name = "AddFriendItem")
    @ApiModelProperty(name = "importFriendItems", value = "好友结构体对象", required = true)
    private ImportFriendItem[]  importFriendItems;
}
