package io.github.liuruinian.ocr.server.autoconfiguration;

import io.github.liuruinian.ocr.core.authtoken.AuthTokenRepository;
import io.github.liuruinian.ocr.core.authtoken.AuthTokenService;
import io.github.liuruinian.ocr.server.authtoken.DefaultAuthTokenService;
import io.github.liuruinian.ocr.server.controller.AuthTokenController;
import io.github.liuruinian.ocr.server.properties.HuaweiOcrProperties;
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
@EnableConfigurationProperties(value = {HuaweiOcrProperties.class})
@ComponentScan(basePackages = {
        "io.github.liuruinian.ocr.server.repository",
        "io.github.liuruinian.ocr.server.controller",
        "io.github.liuruinian.ocr.server.ocrimpl"
})
public class HuaweiOcrAutoConfiguration {

    private final static String BASE_PATH = "/hwocr";

    /**
     * 注入AuthTokenServiceBean
     * @param properties 华为云OCR相关配置参数
     * @param repository Token认证存储接口
     */
    @Bean
    public AuthTokenService authTokenService(HuaweiOcrProperties properties, AuthTokenRepository repository) {
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
    public void setUserSignWebMapping(RequestMappingHandlerMapping mapping,
                                      AuthTokenController controller) throws NoSuchMethodException, SecurityException {

        Method authTokenMethod = AuthTokenController.class.getMethod("getRefreshAuthToken");
        RequestMappingInfo mappingInfo = RequestMappingInfo.paths("/hwapi/auth/token")
                .methods(RequestMethod.GET).build();

        mapping.registerMapping(mappingInfo, controller, authTokenMethod);
    }
}
