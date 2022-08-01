package io.github.liuruinian.im.server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * UserSign Redis配置
 *
 * @author liuruinian
 */
@ConfigurationProperties(prefix = "tim.user-sign.repository.redis")
public class RedisUserSignProperties {

    /**
     * Key前缀
     */
    private String keyPrefix = "tim:im_user_sign:";

    /**
     * 获取Key前缀
     *
     * @return Key前缀
     */
    public String getKeyPrefix() {
        return keyPrefix;
    }

    /**
     * 设置Key前缀
     *
     * @param keyPrefix Key前缀
     */
    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
