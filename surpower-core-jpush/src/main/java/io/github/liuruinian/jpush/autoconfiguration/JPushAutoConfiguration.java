package io.github.liuruinian.jpush.autoconfiguration;

import io.github.liuruinian.jpush.properties.JPushProperties;
import io.github.liuruinian.jpush.service.JPushApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author liuruinian
 * <p>
 *     极光推送自动配置
 * </p>
 */
@Configuration
@EnableConfigurationProperties(value = {JPushProperties.class})
@ComponentScan(basePackages = {"io.github.liuruinian.jpush"})
public class JPushAutoConfiguration {

    /**
     * 将JPushApi注入到Spring容器
     * 可以自定义条件装配，也可以使用@ConditionalOnProperty
     * @see Condition
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     */
    @Bean
    @Conditional(JPushApiCondition.class)
    public JPushApi jPushApi(JPushProperties properties) {
        JPushApi jPushApi = new JPushApi(properties);
        jPushApi.initClient();
        return jPushApi;
    }

    /**
     * @see Conditional
     * 条件装配
     */
    public static class JPushApiCondition implements Condition {

        @SuppressWarnings("NullableProblems")
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Boolean enable = context.getEnvironment().getProperty("jpush.enable", Boolean.class);
            return enable == null || enable;
        }
    }
}
