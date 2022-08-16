package io.github.liuruinian.ocr.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liuruinian
 * @version 2022-08-16 23:34:56
 * <p>
 *     华为云OCR相关配置参数
 * </p>
 */
@Data
@ConfigurationProperties(prefix = "huawei.ocr")
public class HuaweiOcrProperties {

    /**
     * IAM用户名
     */
    private String username;

    /**
     * password为用户密码
     */
    private String password;

    /**
     * domain name
     */
    private String domainName;

    /**
     * project name，如cn-north-4
     */
    private String projectName;

    /**
     * Token认证请求地址
     */
    private String  authTokenUrl = "https://iam.cn-north-4.myhuaweicloud.com";

    /**
     * x-auth-token有效期：默认24小时
     */
    private Long    authTokenExpire = 24L;

    /**
     * x-auth-token缓存key前缀
     */
    private String  authTokenKeyPrefix = "hwocr-auth-token-";
}
