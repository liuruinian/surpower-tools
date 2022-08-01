package io.github.liuruinian.im.core.params.relationchain;

import com.alibaba.fastjson.JSONArray;
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
 * @author hk417
 * @version Created in 2022/2/11 16:43
 * <p>
 *      需要更新的好友对象
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("需要更新的好友对象")
public class UpdateItem implements Serializable {

    @JsonProperty(value = "To_Account")
    @JSONField(name = "To_Account")
    @ApiModelProperty(name = "toAccount", value = "好友的 UserID", required = true)
    private String toAccount;

    @JsonProperty(value = "SnsItem")
    @JSONField(name = "SnsItem")
    @ApiModelProperty(name = "snsItem", value = "需要更新的关系链数据对象数组", required = true)
    private JSONArray snsItem;
}
