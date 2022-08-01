package io.github.liuruinian.im.core.params.accountmanage;

import io.github.liuruinian.im.core.util.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liuruinian
 * @version Created in 2022/2/7 19:21
 * <p>
 *      失效账号登录参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("失效账号登录参数")
public class KickParam implements Serializable {

    @ApiModelProperty(name = "userId", value = "用户名", required = true)
    private String userId;

    public void checkRequired() {
        if (StringUtils.isBlank(userId)) {
            throw new RuntimeException("[KickParam] -> userId can't be null!");
        }
    }
}
