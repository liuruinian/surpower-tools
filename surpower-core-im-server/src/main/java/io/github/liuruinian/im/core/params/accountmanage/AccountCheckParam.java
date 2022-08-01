package io.github.liuruinian.im.core.params.accountmanage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @author liuruinian
 * @version Created in 2022/2/7 14:50
 * <p>
 *      检查账号是否导入参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("检查账号是否导入参数")
public class AccountCheckParam implements Serializable {

    @ApiModelProperty(name = "userIds", value = "用户名集合", required = true)
    private Set<String> userIds;

    public void checkRequired() {
        if (userIds == null || userIds.size() == 0) {
            throw new RuntimeException("[AccountCheckParam] -> userIds can't be empty!");
        }
    }
}
