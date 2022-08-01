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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("待设置的用户的资料")
public class PortraitItem implements Serializable {

    @JsonProperty(value = "Tag")
    @JSONField(name = "Tag")
    @ApiModelProperty(name = "tag", value = "指定要设置的资料字段的名称，支持设置的资料字段有：\n" +
                                            "1.标配资料字段，详情可参见 https://cloud.tencent.com/document/product/269/1500#.E6.A0.87.E9.85.8D.E8.B5.84.E6.96.99.E5.AD.97.E6.AE.B5\n" +
                                            "2.自定义资料字段，详情可参见 https://cloud.tencent.com/document/product/269/1500#.E8.87.AA.E5.AE.9A.E4.B9.89.E8.B5.84.E6.96.99.E5.AD.97.E6.AE.B5", required = true)
    private String tag;

    @JsonProperty(value = "Value")
    @JSONField(name = "Value")
    @ApiModelProperty(name = "value", value = "待设置的资料字段的值, 详情可参见 https://cloud.tencent.com/document/product/269/1500#.E8.B5.84.E6.96.99.E5.AD.97.E6.AE.B5", required = true)
    private Object value;
}