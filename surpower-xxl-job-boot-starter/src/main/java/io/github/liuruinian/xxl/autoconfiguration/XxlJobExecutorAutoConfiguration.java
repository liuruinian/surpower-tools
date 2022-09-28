package io.github.liuruinian.xxl.autoconfiguration;

import io.github.liuruinian.xxl.core.executor.impl.XxlJobSpringExecutor;
import io.github.liuruinian.xxl.properties.XxlJobProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuruinian
 * @version 2022-04-25
 * <p>
 *     XxlJobExecutor自动配置类
 * </p>
 */
@Configuration
@EnableConfigurationProperties(value = {XxlJobProperties.class})
@Slf4j
public class XxlJobExecutorAutoConfiguration {

    @Bean
    public XxlJobSpringExecutor xxlJobSpringExecutor(XxlJobProperties xxlJobProperties) {
        if (log.isInfoEnabled()) {
            log.info("[XxlJobExecutorAutoConfiguration::xxlJobSpringExecutor] -> xxl-job config init.");
        }
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(xxlJobProperties.getAdminAddress());
        xxlJobSpringExecutor.setAppname(xxlJobProperties.getExecutorAppName());
        xxlJobSpringExecutor.setAddress(xxlJobProperties.getExecutorAddress());
        xxlJobSpringExecutor.setIp(xxlJobProperties.getExecutorIp());
        xxlJobSpringExecutor.setPort(xxlJobProperties.getExecutorPort());
        xxlJobSpringExecutor.setAccessToken(xxlJobProperties.getAccessToken());
        xxlJobSpringExecutor.setLogPath(xxlJobProperties.getExecutorLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(xxlJobProperties.getExecutorLogRetentionDays());
        return xxlJobSpringExecutor;
    }
}
