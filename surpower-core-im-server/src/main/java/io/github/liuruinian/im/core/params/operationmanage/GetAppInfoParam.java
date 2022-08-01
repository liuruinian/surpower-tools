package io.github.liuruinian.im.core.params.operationmanage;

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
 * 拉取运营数据参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("拉取运营数据参数")
public class GetAppInfoParam implements Serializable {

    @JsonProperty(value = "RequestField")
    @JSONField(name = "RequestField")
    @ApiModelProperty(name = "requestField", value = "该字段用来指定需要拉取的运营数据，不填默认拉取所有字段, 参阅：https://cloud.tencent.com/document/product/269/4193#operation")
    private String[] requestField;
}
