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
@ApiModel("活动列表请求参数")
public class ActivityListParam implements Serializable {

    @ApiModelProperty(name = "activityType", value = "活动类型" +
            "0：抽奖类活动\n" +
            "1：游戏类活动\n" +
            "2：裂变类活动\n" +
            "3：支付类活动\n" +
            "4：长期类活动\n" +
            "5：投票类活动\n" +
            "6：答题类活动\n" +
            "7：工具类活动\n" +
            "8：红包类活动")
    private Integer activityType;

    @ApiModelProperty(name = "sort", value = "排序字段(正序排序，默认值 id)" +
            "id：活动id\n" +
            "view：浏览人数\n" +
            "player：参与人数\n" +
            "createTime：创建时间\n" +
            "startTime：开始时间\n" +
            "endTime：结束时间")
    private String  sort;

    @ApiModelProperty(name = "pageNo", value = "页数（默认1）")
    private Integer pageNo;

    @ApiModelProperty(name = "pageSize", value = "返回的最大记录数（默认10）")
    private Integer pageSize;
}
