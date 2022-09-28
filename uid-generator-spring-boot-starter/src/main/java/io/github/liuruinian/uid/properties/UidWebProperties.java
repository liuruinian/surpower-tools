package io.github.liuruinian.uid.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hk417
 * @version Created in 2022/1/16 3:08
 * <p>
 *     UID WEB Endpoint Config
 * </p>
 */
@Data
@ConfigurationProperties(prefix = "baidu.uid.web")
public class UidWebProperties {
    /**
     * 默认基础路径
     */
    public static final String DEFAULT_BASE_PATH = "/baidu";

    /**
     * 是否启用web端点
     */
    private boolean enable = false;

    /**
     * 基础路径
     */
    private String basePath = DEFAULT_BASE_PATH;

    /**
     * 是否启用uid生成
     */
    private boolean enableUidGenerate;
}
