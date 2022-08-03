package io.github.liuruinian.jpush.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author liuruinian
 * <p>
 *     待推送消息
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PushMessage implements Serializable {


    /**
     * 标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 角标
     */
    private Integer badge;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 附加业务参数
     */
    private Map<String, String> extras;
}
