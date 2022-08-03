package io.github.liuruinian.jpush.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liuruinian
 * <p>
 *     极光推送配置
 * </p>
 */
@Data
@ConfigurationProperties(prefix = "jpush")
public class JPushProperties {

    /**
     * 是否启用极光推送, 默认启用
     */
    private Boolean     enable = true;

    /**
     * masterSecret(从极光后台获得)
     */
    private String      masterSecret;

    /**
     * appKey(从极光后台获得)
     */
    private String      appKey;

    /**
     * 是否启用代理服务器
     */
    private Boolean     useProxy = false;

    /**
     * 代理服务器主机名或IP
     */
    private String      proxyHost;

    /**
     * 代理服务器端口号
     */
    private int         proxyPort;

    /**
     * 代理服务器用户名
     */
    private String      proxyUsername;

    /**
     * 代理服务器密码
     */
    private String      proxyPassword;

    /**
     * 重试时间间隔(毫秒)
     */
    private Long        retryInterval = 500L;

    /**
     * 最大重试次数(0表示不重试)
     */
    private Integer     retryMaxAttempts = 0;
}
