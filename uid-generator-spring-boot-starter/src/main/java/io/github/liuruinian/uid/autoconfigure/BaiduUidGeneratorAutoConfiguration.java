package io.github.liuruinian.uid.autoconfigure;

import io.github.liuruinian.uid.controller.UidGeneratorController;
import io.github.liuruinian.uid.impl.CachedUidGenerator;
import io.github.liuruinian.uid.impl.DefaultUidGenerator;
import io.github.liuruinian.uid.properties.UidWebProperties;
import io.github.liuruinian.uid.utils.StringUtils;
import io.github.liuruinian.uid.worker.DisposableWorkerIdAssigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author hk417
 * @version  Created in 2022/1/16 2:39
 * <p>
 *     BaiduUidGeneratorAutoConfiguration
 * </p>
 */
@Configuration
@EnableConfigurationProperties(value = {UidWebProperties.class})
public class BaiduUidGeneratorAutoConfiguration {

    @Bean
    public DisposableWorkerIdAssigner disposableWorkerIdAssigner() {
        return new DisposableWorkerIdAssigner();
    }

    @Bean(value = "defaultUidGenerator")
    public DefaultUidGenerator initDefaultUid(DisposableWorkerIdAssigner disposableWorkerIdAssigner){
        DefaultUidGenerator defaultUidGenerator = new DefaultUidGenerator();
        defaultUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner);
        defaultUidGenerator.setTimeBits(29);
        defaultUidGenerator.setWorkerBits(21);
        defaultUidGenerator.setSeqBits(13);
        defaultUidGenerator.setEpochStr(LocalDate.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return defaultUidGenerator;
    }

    @Bean(value ="cachedUidGenerator")
    public CachedUidGenerator initCachedUidGenerator(DisposableWorkerIdAssigner disposableWorkerIdAssigner){
        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        cachedUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner);
        return cachedUidGenerator;
    }

    /**
     * UidGeneratorController????????????
     */
    private static String getBasePath(UidWebProperties properties) {
        if (properties == null) {
            return UidWebProperties.DEFAULT_BASE_PATH;
        }

        String bathPath = StringUtils.trimToNull(properties.getBasePath());

        return bathPath == null ? UidWebProperties.DEFAULT_BASE_PATH : bathPath;
    }

    /**
     * ?????????????????????
     *
     * @param mapping
     *         RequestMappingHandlerMapping
     * @param properties
     *         UID Web ??????
     * @param controller
     *         UidGeneratorController
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
    public void setWebMapping(RequestMappingHandlerMapping mapping, UidWebProperties properties,
                              UidGeneratorController controller) throws NoSuchMethodException, SecurityException {

        // ??????web????????????????????????????????????uid??????????????????
        if (properties == null || !properties.isEnable()) {
            return;
        }

        // ??????????????????????????????@RequestMapping(path = "/baidu")
        String bathPath = getBasePath(properties);

        // ????????????????????????
        if (properties.isEnableUidGenerate()) {
            // ??????????????????uid??????
            Method sendMethod = UidGeneratorController.class.getMethod("uidGenerate");
            // ??????Request??????
            RequestMappingInfo uidGenerateInfo = RequestMappingInfo.paths(bathPath + "/uid/generate")
                    .methods(RequestMethod.POST).build();
            mapping.registerMapping(uidGenerateInfo, controller, sendMethod);
        }
    }
}
