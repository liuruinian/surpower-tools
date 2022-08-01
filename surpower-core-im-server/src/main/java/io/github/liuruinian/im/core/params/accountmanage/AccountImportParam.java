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
 * @version 2022-02-06
 * <p>
 *     导入单个账号请求参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("导入单个账号请求参数")
public class AccountImportParam implements Serializable {

    @ApiModelProperty(name = "userId", value = "用户名，长度不超过32字节", required = true)
    private String userId;

    @ApiModelProperty(name = "nick", value = "用户昵称")
    private String nick;

    @ApiModelProperty(name = "faceUrl", value = "用户头像URL")
    private String faceUrl;

    public void checkRequired() {
        if (StringUtils.isBlank(userId)) {
            throw new RuntimeException("[AccountImportParam] -> userId not allow empty!");
        }
    }
}
