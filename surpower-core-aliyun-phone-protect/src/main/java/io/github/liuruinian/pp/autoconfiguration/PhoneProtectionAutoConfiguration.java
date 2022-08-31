package io.github.liuruinian.pp.autoconfiguration;

import com.alicom.mns.tools.DefaultAlicomMessagePuller;
import io.github.liuruinian.pp.controller.BindController;
import io.github.liuruinian.pp.domain.AxnBindParam;
import io.github.liuruinian.pp.properties.AliyunProperties;
import io.github.liuruinian.pp.properties.AsyncProperties;
import io.github.liuruinian.pp.properties.PhoneProtectWebProperties;
import io.github.liuruinian.pp.provider.bind.BindProvider;
import io.github.liuruinian.pp.service.bind.BindManager;
import io.github.liuruinian.pp.threadpool.AsyncThreadPoolExecutor;
import io.github.liuruinian.pp.threadpool.DefaultAsyncThreadPoolExecutor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author liuruinian
 * <p>
 *      号码隐私保护自动配置
 * </p>
 */
@Configuration
@EnableConfigurationProperties(value = {
        AliyunProperties.class,
        AsyncProperties.class,
        PhoneProtectWebProperties.class
})
@ComponentScan(basePackages = {
        "io.github.liuruinian.pp.controller",
        "io.github.liuruinian.pp.handler"
})
public class PhoneProtectionAutoConfiguration {

    /**
     * 构造异步处理线程池
     *
     * @param properties 绑定异步配置
     * @return 绑定异步处理线程池
     */
    @Bean
    @ConditionalOnProperty(name = "phone.protection.async.enable", havingValue = "true")
    @ConditionalOnMissingBean(AsyncThreadPoolExecutor.class)
    public AsyncThreadPoolExecutor sendAsyncThreadPoolExecutor(AsyncProperties properties) {
        return new DefaultAsyncThreadPoolExecutor(properties);
    }

    /**
     * aliyun消息回执拉取器
     */
    @Bean
    @Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE)
    @Conditional(DefaultAlicomMessagePullerCondition.class)
    public DefaultAlicomMessagePuller defaultAlicomMessagePuller() {
        return new DefaultAlicomMessagePuller();
    }

    /**
     * @see Conditional
     * 条件装配
     */
    public static class DefaultAlicomMessagePullerCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            ClassLoader classLoader = context.getClassLoader();
            try {
                assert classLoader != null;
                classLoader.loadClass("com.alicom.mns.tools.DefaultAlicomMessagePuller");
                return true;
            } catch (ClassNotFoundException e) {
                return false;
            }
        }
    }

    /**
     * 构造BindManager
     *
     * @param properties     阿里云配置信息
     * @param eventPublisher spring事件发布器
     */
    @Bean
    public BindManager bindManager(AliyunProperties properties, ApplicationEventPublisher eventPublisher) {
        return new BindProvider(properties, eventPublisher);
    }

    /**
     * 设置控制器映射
     *
     * @param mapping
     *         RequestMappingHandlerMapping
     * @param properties
     *         aliyun号码隐私保护web配置
     * @param controller
     *         Aliyun Phone Number Protection Controller
     * @throws NoSuchMethodException
     *         if a matching method is not found
     *         or if the name is "&lt;init&gt;"or "&lt;clinit&gt;".
     * @throws SecurityException
     *         If a security manager, <i>s</i>, is present and
     *         the caller's class loader is not the same as or an
     *         ancestor of the class loader for the current class and
     *         invocation of {@link SecurityManager#checkPackageAccess
     *         s.checkPackageAccess()} denies access to the package
     *         of this class.
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setBindWebMapping(RequestMappingHandlerMapping mapping, PhoneProtectWebProperties properties,
                              BindController controller) throws NoSuchMethodException, SecurityException {
        // 如果web不启用直接返回，那么号码隐私接口也不可用
        if (properties == null || !properties.getEnable()) {
            return;
        }

        // 获取请求根路径，对应@RequestMapping(path = "/pnp")
        String bathPath = getBasePath(properties);

        // 是否启用AXN绑定接口
        if (properties.getEnableBindAxn()) {
            // 反射得到AXN绑定方法
            Method bindAxnMethod = BindController.class.getMethod("bindAxn", AxnBindParam.class);
            // 构建Request请求
            RequestMappingInfo bindAxnInfo = RequestMappingInfo.paths(bathPath + "/bind/axn")
                    .methods(RequestMethod.POST).build();
            mapping.registerMapping(bindAxnInfo, controller, bindAxnMethod);
        }
    }

    /**
     * Aliyun Phone Number Protection Controller请求路径
     */
    private static String getBasePath(PhoneProtectWebProperties properties) {
        if (properties == null) {
            return PhoneProtectWebProperties.DEFAULT_BASE_PATH;
        }

        String bathPath = StringUtils.trimToNull(properties.getBasePath());

        return bathPath == null ? PhoneProtectWebProperties.DEFAULT_BASE_PATH : bathPath;
    }
}
