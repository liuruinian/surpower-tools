package io.github.liuruinian.fanco.core.entity;

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
@ApiModel("无感进入活动请求参数")
public class NoSenseActivity implements Serializable {

    @ApiModelProperty(name = "authUid", value = "合作方授权C端用户ID", required = true)
    private String authUid;

    @ApiModelProperty(name = "activityUrl", value = "活动地址", required = true)
    private String activityUrl;
}
