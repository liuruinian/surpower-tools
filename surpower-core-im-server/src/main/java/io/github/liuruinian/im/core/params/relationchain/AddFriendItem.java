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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("好友结构体对象")
public class AddFriendItem implements Serializable {

    @JsonProperty(value = "To_Account")
    @JSONField(name = "To_Account")
    @ApiModelProperty(name = "toAccount", value = "好友的 UserID", required = true)
    private String toAccount;

    @JsonProperty(value = "Remark")
    @JSONField(name = "Remark")
    @ApiModelProperty(name = "remark", value =   "From_Account 对 To_Account 的好友备注, 备注长度最长不得超过 96 个字节")
    private String remark;

    @JsonProperty(value = "GroupName")
    @JSONField(name = "GroupName")
    @ApiModelProperty(name = "groupName", value =   "From_Account 对 To_Account 的分组信息，添加好友时只允许设置一个分组")
    private String groupName;

    @JsonProperty(value = "AddSource")
    @JSONField(name = "AddSource")
    @ApiModelProperty(name = "addSource", value =   "加好友来源字段, 加好友来源字段的前缀是：AddSource_Type_\n" +
                                                    "关键字：必须是英文字母，且长度不得超过 8 字节，建议用一个英文单词或该英文单词的缩写；\n" +
                                                    "示例：加好友来源的关键字是 Android，则加好友来源字段是：AddSource_Type_Android", required = true)
    private String addSource;

    @JsonProperty(value = "AddWording")
    @JSONField(name = "AddWording")
    @ApiModelProperty(name = "addWording", value = "From_Account 和 To_Account 形成好友关系时的附言信息, 加好友附言的长度最长不得超过 256 个字节")
    private String addWording;
}
