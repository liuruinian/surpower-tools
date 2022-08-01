package io.github.liuruinian.im.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "tim")
public class ImServerProperties {

    /**
     * 应用SDKAppId
     */
    private Long    sdkAppId;

    /**
     * 应用密钥
     */
    private String  secretKey;

    /**
     * App管理员
     */
    private String  appManager = "administrator";
}
