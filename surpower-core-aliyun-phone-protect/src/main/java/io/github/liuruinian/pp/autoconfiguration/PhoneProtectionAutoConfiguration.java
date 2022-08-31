package io.github.liuruinian.pp.autoconfiguration;

import io.github.liuruinian.pp.properties.AliyunProperties;
import io.github.liuruinian.pp.properties.AsyncProperties;
import io.github.liuruinian.pp.properties.PhoneProtectWebProperties;
import io.github.liuruinian.pp.provider.bind.BindProvider;
import io.github.liuruinian.pp.service.bind.BindManager;
import io.github.liuruinian.pp.threadpool.AsyncThreadPoolExecutor;
import io.github.liuruinian.pp.threadpool.DefaultAsyncThreadPoolExecutor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuruinian
 * <p>
 * 号码隐私保护自动配置
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
     * 构造BindManager
     *
     * @param properties     阿里云配置信息
     * @param eventPublisher spring事件发布器
     */
    @Bean
    public BindManager bindManager(AliyunProperties properties, ApplicationEventPublisher eventPublisher) {
        return new BindProvider(properties, eventPublisher);
    }
}
