package io.github.liuruinian.im.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "tim.user-sign")
public class UserSignProperties {

    /**
     * 用户签名有效期（单位：秒）, 默认：7天
     */
    private long    expire = 7 * 24 * 3600L;
}
