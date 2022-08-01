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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("导入好友结构体对象")
public class ImportFriendItem implements Serializable {

    @JsonProperty(value = "To_Account")
    @JSONField(name = "To_Account")
    @ApiModelProperty(name = "toAccount", value = "好友的 UserID", required = true)
    private String toAccount;

    @JsonProperty(value = "Remark")
    @JSONField(name = "Remark")
    @ApiModelProperty(name = "remark", value =   "From_Account 对 To_Account 的好友备注, 备注长度最长不得超过 96 个字节")
    private String remark;

    @JsonProperty(value = "RemarkTime")
    @JSONField(name = "RemarkTime")
    @ApiModelProperty(name = "remarkTime", value = "From_Account 对 To_Account 的好友备注时间")
    private Integer remarkTime;

    @JsonProperty(value = "GroupName")
    @JSONField(name = "GroupName")
    @ApiModelProperty(name = "groupName", value =   "From_Account 对 To_Account 的分组信息，详情可参见: https://cloud.tencent.com/document/product/269/1501#.E6.A0.87.E9.85.8D.E5.A5.BD.E5.8F.8B.E5.AD.97.E6.AE.B5")
    private String[] groupName;

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

    @JsonProperty(value = "AddTime")
    @JSONField(name = "AddTime")
    @ApiModelProperty(name = "addTime", value = "From_Account 和 To_Account 形成好友关系的时间")
    private Integer addTime;

    @JsonProperty(value = "CustomItem")
    @JSONField(name = "CustomItem")
    @ApiModelProperty(name = "customItem", value =  "From_Account 对 To_Account 的自定义好友数据，每一个成员都包含一个 Tag 字段和一个 Value 字段\n" +
                                                    "详情可参见: https://cloud.tencent.com/document/product/269/1501#.E8.87.AA.E5.AE.9A.E4.B9.89.E5.A5.BD.E5.8F.8B.E5.AD.97.E6.AE.B5")
    private JSONArray customItem;
}
