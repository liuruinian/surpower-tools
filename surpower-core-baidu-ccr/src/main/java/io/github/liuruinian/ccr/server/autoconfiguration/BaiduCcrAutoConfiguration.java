package io.github.liuruinian.ccr.server.autoconfiguration;

import io.github.liuruinian.ccr.core.authtoken.AuthTokenRepository;
import io.github.liuruinian.ccr.core.authtoken.AuthTokenService;
import io.github.liuruinian.ccr.server.authtoken.DefaultAuthTokenService;
import io.github.liuruinian.ccr.server.controller.AuthTokenController;
import io.github.liuruinian.ccr.server.properties.BaiduCcrProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@Configuration
@EnableConfigurationProperties(value = {BaiduCcrProperties.class})
@ComponentScan(basePackages = {
        "io.github.liuruinian.ccr.server.repository",
        "io.github.liuruinian.ccr.server.controller",
        "io.github.liuruinian.ccr.server.ccrimpl"
})
public class BaiduCcrAutoConfiguration {

    private final static String BASE_PATH = "/baidu/ccr";

    @Bean
    public AuthTokenService authTokenService(BaiduCcrProperties properties, AuthTokenRepository repository) {
        return new DefaultAuthTokenService(properties, repository);
    }

    /**
     * 设置AuthToken控制器映射
     *
     * @param mapping
     *         RequestMappingHandlerMapping
     * @param controller
     *         AuthTokenController
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
    public void setAuthTokenWebMapping(RequestMappingHandlerMapping mapping,
                                       AuthTokenController controller) throws NoSuchMethodException, SecurityException {

        Method authTokenMethod = AuthTokenController.class.getMethod("getRefreshAuthToken");
        RequestMappingInfo mappingInfo = RequestMappingInfo.paths("/baidu/auth/token")
                .methods(RequestMethod.POST).build();

        mapping.registerMapping(mappingInfo, controller, authTokenMethod);
    }
}
