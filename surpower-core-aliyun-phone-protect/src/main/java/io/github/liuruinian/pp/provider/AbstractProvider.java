package io.github.liuruinian.pp.provider;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import io.github.liuruinian.pp.exception.PoolKeyIsNullException;
import io.github.liuruinian.pp.properties.AliyunProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;

public abstract class AbstractProvider {

    /** 请求成功标识 */
    protected static final String               OK = "OK";

    /** 隐私号码产品名称（隐私保护产品名固定，无需修改）*/
    protected static final String               PRODUCT = "Dyplsapi";

    /** 隐私号码产品域名（接口地址固定，无需修改）*/
    protected static final String               DOMAIN = "dyplsapi.aliyuncs.com";

    protected final IAcsClient                  acsClient;

    /** 号码池 */
    protected final String                      poolKey;

    /**
     * aliyun号码隐私保护配置
     */
    protected AliyunProperties                  properties;

    /**
     * spring 事件发布器
     */
    protected final ApplicationEventPublisher   eventPublisher;

    public AbstractProvider(AliyunProperties properties, ApplicationEventPublisher eventPublisher) {
        this.properties = properties;
        this.eventPublisher = eventPublisher;
        this.poolKey = properties.getPoolKey();

        if (StringUtils.isBlank(poolKey)) {
            throw new PoolKeyIsNullException();
        }

        String endPoint = properties.getEndpoint();
        String accessKeyId = properties.getAccessKeyId();
        String accessKeySecret = properties.getAccessKeySecret();

        IClientProfile profile = DefaultProfile.getProfile(endPoint, accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint(endPoint, PRODUCT, DOMAIN);

        acsClient = new DefaultAcsClient(profile);
    }
}
