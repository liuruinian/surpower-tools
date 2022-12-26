package io.github.liuruinian.fanco.server.autoconfigure;

import io.github.liuruinian.fanco.core.constants.FancoConstants;
import io.github.liuruinian.fanco.core.token.AuthTokenRepository;
import io.github.liuruinian.fanco.core.token.AuthTokenService;
import io.github.liuruinian.fanco.server.controller.auth.AuthTokenController;
import io.github.liuruinian.fanco.server.properties.FancoProperties;
import io.github.liuruinian.fanco.server.token.DefaultAuthTokenService;
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

/**
 * fanco auto configuration
 *
 * @author liuruinian
 * @version 2022-12-26
 */
@Configuration
@ComponentScan(basePackages = {
        "io.github.liuruinian.fanco.server.repository",
        "io.github.liuruinian.fanco.server.controller"
})
@EnableConfigurationProperties(value = {FancoProperties.class})
public class FancoAutoConfiguration {

    /**
     * create auth token service bean
     *
     * @param properties fanco properties
     * @param repository token repository
     * @return auth token service bean
     */
    @Bean
    public AuthTokenService authTokenService(FancoProperties properties, AuthTokenRepository repository) {
        return new DefaultAuthTokenService(properties, repository);
    }

    /**
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
        Method method = AuthTokenController.class.getMethod("oauthAccessToken");
        RequestMappingInfo mappingInfo = RequestMappingInfo.paths(FancoConstants.BASE_PATH + "/oauth/partner/token")
                .methods(RequestMethod.POST).build();

        mapping.registerMapping(mappingInfo, controller, method);
    }
}
