package io.github.liuruinian.pp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author liuruinian
 * 号码隐私保护web端点配置
 */
@Data
@ConfigurationProperties(prefix = "phone.protection.web")
public class PhoneProtectWebProperties implements Serializable {

    /**
     * 默认基础路径
     */
    public static final String DEFAULT_BASE_PATH = "/pnp";

    /**
     * 基础路径
     */
    private String basePath = DEFAULT_BASE_PATH;

    /**
     * 是否启用web端点
     */
    private Boolean enable = false;

    /**
     * 是否启用AXN绑定接口
     */
    private Boolean enableBindAxn = true;

    /**
     * 是否启用绑定详情接口
     */
    private Boolean enableBindDetail = true;

    /**
     * 是否启用解绑接口
     */
    private Boolean enableUnbind = true;

    /**
     * 是否启用更新绑定关系接口
     */
    private Boolean enableUpdateBind = true;

    /**
     * 是否启用隐私号码购买接口
     */
    private Boolean enableBuySecret = true;

    /**
     * 是否启用释放隐私号码接口
     */
    private Boolean enableReleaseSecret = true;

    /**
     * 是否启用获取录音文件接口
     */
    private Boolean enableObtainSecretRecord = true;

    /**
     * 是否启用查询呼叫可用性接口
     */
    private Boolean enableCallAvailable = true;
}
