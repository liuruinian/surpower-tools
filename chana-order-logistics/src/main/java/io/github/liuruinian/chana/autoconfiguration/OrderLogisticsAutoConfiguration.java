package io.github.liuruinian.chana.autoconfiguration;

import io.github.liuruinian.chana.controller.OrderLogisticsController;
import io.github.liuruinian.chana.impl.DefaultOrderLogisticsService;
import io.github.liuruinian.chana.service.OrderLogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import java.lang.reflect.Method;

@Configuration
@ComponentScan(basePackages = {
        "io.github.liuruinian.chana.controller"
})
public class OrderLogisticsAutoConfiguration {

    /**
     * @param jdbcTemplate jdbc template
     */
    @Bean
    public OrderLogisticsService orderLogisticsService(JdbcTemplate jdbcTemplate) {
        return new DefaultOrderLogisticsService(jdbcTemplate);
    }

    /**
     * @param mapping    RequestMappingHandlerMapping
     * @param controller OrderLogisticsController
     * @throws NoSuchMethodException if a matching method is not found
     *                               or if the name is "&lt;init&gt;"or "&lt;clinit&gt;".
     * @throws SecurityException     If a security manager, <i>s</i>, is present and
     *                               the caller's class loader is not the same as or an
     *                               ancestor of the class loader for the current class and
     *                               invocation of {@link SecurityManager#checkPackageAccess
     *                               s.checkPackageAccess()} denies access to the package
     *                               of this class.
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setAuthTokenWebMapping(RequestMappingHandlerMapping mapping,
                                       OrderLogisticsController controller) throws NoSuchMethodException, SecurityException {
        Method method = OrderLogisticsController.class.getMethod("importOrderLogistics", MultipartFile.class);
        RequestMappingInfo mappingInfo = RequestMappingInfo.paths("/order/logistics/import")
                .methods(RequestMethod.POST).build();

        mapping.registerMapping(mappingInfo, controller, method);
    }
}
