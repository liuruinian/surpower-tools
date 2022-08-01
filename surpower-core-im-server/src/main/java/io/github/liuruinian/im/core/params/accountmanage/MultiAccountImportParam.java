package io.github.liuruinian.im.core.params.accountmanage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuruinian
 * @version Created in 2022/2/7 13:36
 * <p>
 *      导入多个账号参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("导入多个账号参数")
public class MultiAccountImportParam implements Serializable {

    @ApiModelProperty(name = "accounts", value = "用户名，单个用户名长度不超过32字节，单次最多导入100个用户名", required = true)
    private List<String> accounts;

    public void checkRequired() {
        if (accounts == null || accounts.size() == 0) {
            throw new RuntimeException("[MultiAccountImportParam] -> accounts can't be empty!");
        }
    }
}
