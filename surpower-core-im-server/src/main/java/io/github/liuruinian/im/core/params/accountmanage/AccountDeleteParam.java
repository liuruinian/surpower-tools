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
 *      删除账号参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("删除账号参数")
public class AccountDeleteParam implements Serializable {

    @ApiModelProperty(name = "userIds", value = "用户名集合", required = true)
    private Set<String> userIds;

    public void checkRequired() {
        if (userIds == null || userIds.size() == 0) {
            throw new RuntimeException("[AccountDeleteParam] -> userIds can't be empty!");
        }
    }
}
