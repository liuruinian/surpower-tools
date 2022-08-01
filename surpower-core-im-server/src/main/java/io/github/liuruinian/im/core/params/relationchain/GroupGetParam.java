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
 *     拉取分组参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("拉取分组参数")
public class GroupGetParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "指定要拉取分组的用户的 UserID", required = true)
    private String fromAccount;

    @JsonProperty(value = "NeedFriend")
    @JSONField(name = "NeedFriend")
    @ApiModelProperty(name = "needFriend", value = "是否需要拉取分组下的 User 列表, Need_Friend_Type_Yes: 需要拉取, 不填时默认不拉取, 只有 GroupName 为空时有效")
    private String needFriend;

    @JsonProperty(value = "GroupName")
    @JSONField(name = "GroupName")
    @ApiModelProperty(name = "groupName", value = "要拉取的分组名称")
    private String[] groupName;
}
