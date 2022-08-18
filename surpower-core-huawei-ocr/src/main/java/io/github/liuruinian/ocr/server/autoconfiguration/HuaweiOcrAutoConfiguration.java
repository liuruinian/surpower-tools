package io.github.liuruinian.ocr.server.autoconfiguration;

import io.github.liuruinian.ocr.core.authtoken.AuthTokenRepository;
import io.github.liuruinian.ocr.core.authtoken.AuthTokenService;
import io.github.liuruinian.ocr.core.param.OcrDriverLicenseParam;
import io.github.liuruinian.ocr.core.param.OcrIdCardParam;
import io.github.liuruinian.ocr.core.param.OcrVehicleLicenseParam;
import io.github.liuruinian.ocr.server.authtoken.DefaultAuthTokenService;
import io.github.liuruinian.ocr.server.controller.AuthTokenController;
import io.github.liuruinian.ocr.server.controller.OcrController;
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
    public void setAuthTokenWebMapping(RequestMappingHandlerMapping mapping,
                                      AuthTokenController controller) throws NoSuchMethodException, SecurityException {

        Method authTokenMethod = AuthTokenController.class.getMethod("getRefreshAuthToken");
        RequestMappingInfo mappingInfo = RequestMappingInfo.paths("/hwapi/auth/token")
                .methods(RequestMethod.GET).build();

        mapping.registerMapping(mappingInfo, controller, authTokenMethod);
    }

    /**
     * 设置OCR控制器映射
     *
     * @param mapping
     *         RequestMappingHandlerMapping
     * @param controller
     *         OcrController
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
    public void setOcrWebMapping(RequestMappingHandlerMapping mapping,
                                 OcrController controller) throws NoSuchMethodException, SecurityException {

        // id-card
        Method idCardMethod = OcrController.class.getMethod("ocrIdCard", OcrIdCardParam.class);
        RequestMappingInfo idCardMappingInfo = RequestMappingInfo.paths(BASE_PATH + HuaweiCloudOcrApiConstant.OCR_ID_CARD_API)
                .methods(RequestMethod.POST).build();

        mapping.registerMapping(idCardMappingInfo, controller, idCardMethod);

        // vehicle-license
        Method vehicleLicenseMethod = OcrController.class.getMethod("ocrVehicleLicense", OcrVehicleLicenseParam.class);
        RequestMappingInfo vehicleLicenseMappingInfo = RequestMappingInfo.paths(BASE_PATH + HuaweiCloudOcrApiConstant.OCR_VEHICLE_LICENSE_API)
                .methods(RequestMethod.POST).build();

        mapping.registerMapping(vehicleLicenseMappingInfo, controller, vehicleLicenseMethod);

        // driver-license
        Method driverLicenseMethod = OcrController.class.getMethod("ocrDriverLicense", OcrDriverLicenseParam.class);
        RequestMappingInfo driverLicenseMappingInfo = RequestMappingInfo.paths(BASE_PATH + HuaweiCloudOcrApiConstant.OCR_DRIVER_LICENSE_API)
                .methods(RequestMethod.POST).build();

        mapping.registerMapping(driverLicenseMappingInfo, controller, driverLicenseMethod);

        // mvs-invoice
        Method mvsInvoiceMethod = OcrController.class.getMethod("ocrMvsInvoice", OcrMvsInvoiceParam.class);
        RequestMappingInfo mvsInvoiceMappingInfo = RequestMappingInfo.paths(BASE_PATH + HuaweiCloudOcrApiConstant.OCR_MVS_INVOICE_API)
                .methods(RequestMethod.POST).build();

        mapping.registerMapping(mvsInvoiceMappingInfo, controller, mvsInvoiceMethod);

        // bankcard
        Method bankcardMethod = OcrController.class.getMethod("ocrBankCard", OcrBankCardParam.class);
        RequestMappingInfo bankcardMappingInfo = RequestMappingInfo.paths(BASE_PATH + HuaweiCloudOcrApiConstant.OCR_BANKCARD_API)
                .methods(RequestMethod.POST).build();

        mapping.registerMapping(bankcardMappingInfo, controller, bankcardMethod);

        // general-text
        Method generalTextMethod = OcrController.class.getMethod("ocrGeneralText", OcrGeneralTextParam.class);
        RequestMappingInfo generalTextMappingInfo = RequestMappingInfo.paths(BASE_PATH + HuaweiCloudOcrApiConstant.OCR_GENERAL_TEXT_API)
                .methods(RequestMethod.POST).build();

        mapping.registerMapping(generalTextMappingInfo, controller, generalTextMethod);
    }
}
