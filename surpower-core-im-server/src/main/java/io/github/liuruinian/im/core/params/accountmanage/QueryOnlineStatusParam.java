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
 * @version Created in 2022/2/7 21:11
 * <p>
 *      查询账号在线状态参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("查询账号在线状态参数")
public class QueryOnlineStatusParam implements Serializable {

    @ApiModelProperty(name = "userIds", value = "需要查询这些 UserID 的登录状态，一次最多查询500个 UserID 的状态", required = true)
    private Set<String> userIds;

    @ApiModelProperty(name = "isNeedDetail", value = "是否需要返回详细的登录平台信息。0表示不需要，1表示需要")
    private Integer isNeedDetail;

    public void checkRequired() {
        if (userIds == null || userIds.size() == 0) {
            throw new RuntimeException("[QueryOnlineStatusParam] -> userIds can't be empty!");
        }
    }
}
