package io.github.liuruinian.im.core.params.portrait;

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
 * 拉取资料请求参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("拉取资料请求参数")
public class PortraitGetParam implements Serializable {

    @JsonProperty(value = "To_Account")
    @JSONField(name = "To_Account")
    @ApiModelProperty(name = "toAccount", value =   "需要拉取资料的 UserID\n" +
                                                    "注意：每次拉取的用户数不得超过100，避免因回包数据量太大以致回包失败", required = true)
    private String[] toAccount;

    @JsonProperty(value = "TagList")
    @JSONField(name = "TagList")
    @ApiModelProperty(name = "tagList", value = "指定要拉取的资料字段的 Tag, 支持的字段有：\n" +
                                                "1.标配资料字段，详情可参见 https://cloud.tencent.com/document/product/269/1500#.E6.A0.87.E9.85.8D.E8.B5.84.E6.96.99.E5.AD.97.E6.AE.B5\n" +
                                                "2.自定义资料字段，详情可参见 https://cloud.tencent.com/document/product/269/1500#.E8.87.AA.E5.AE.9A.E4.B9.89.E8.B5.84.E6.96.99.E5.AD.97.E6.AE.B5", required = true)
    private String[] tagList;
}
