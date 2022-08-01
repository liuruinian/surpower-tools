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
 * @version Created in 2022/2/11 16:40
 * <p>
 *      更新好友参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("更新好友参数")
public class FriendUpdateParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "需要更新该 UserID 的关系链数据", required = true)
    private String          fromAccount;

    @JsonProperty(value = "UpdateItem")
    @JSONField(name = "UpdateItem")
    @ApiModelProperty(name = "updateItem", value = "需要更新的好友对象数组", required = true)
    private UpdateItem[]    updateItem;
}
