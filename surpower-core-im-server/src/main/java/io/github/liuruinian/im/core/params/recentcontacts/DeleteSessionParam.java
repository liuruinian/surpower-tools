package io.github.liuruinian.im.core.params.recentcontacts;

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
 * @author liuruinian
 * @version 2022-02-14
 * <p>
 *     删除单个会话参数
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("删除单个会话参数")
public class DeleteSessionParam implements Serializable {

    @JsonProperty(value = "From_Account")
    @JSONField(name = "From_Account")
    @ApiModelProperty(name = "fromAccount", value = "请求删除该 UserID 的会话", required = true)
    private String  fromAccount;

    @JsonProperty(value = "Type")
    @JSONField(name = "Type")
    @ApiModelProperty(name = "type", value = "会话类型：1 表示 C2C 会话；2 表示 G2C 会话", required = true)
    private Integer type;

    @JsonProperty(value = "To_Account")
    @JSONField(name = "To_Account")
    @ApiModelProperty(name = "toAccount", value = "C2C 会话才赋值，C2C 会话方的 UserID")
    private String  toAccount;

    @JsonProperty(value = "ToGroupid")
    @JSONField(name = "ToGroupid")
    @ApiModelProperty(name = "toGroupid", value = "G2C 会话才赋值，G2C 会话的群 ID")
    private String  toGroupid;

    @JsonProperty(value = "ClearRamble")
    @JSONField(name = "ClearRamble")
    @ApiModelProperty(name = "clearRamble", value = "是否清理漫游消息：1 表示清理漫游消息；0 表示不清理漫游消息")
    private Integer clearRamble;
}
