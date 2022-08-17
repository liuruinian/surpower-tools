package io.github.liuruinian.ocr.core.constant;

/**
 * @author liuruinian
 * @version 2022-08-16 23:34:56
 * <p>
 *     华为云OCR相关API接口常量类
 * </p>
 */
public class HuaweiCloudOcrApiConstant {

    /**
     * Token认证API
     */
    public static final String X_AUTH_TOKEN_API = "/v3/auth/tokens";

    /**
     * 身份证识别API
     */
    public static final String OCR_ID_CARD_API = "/ocr/id-card";

    /**
     * 行驶证识别API
     */
    public static final String OCR_VEHICLE_LICENSE_API = "/ocr/vehicle-license";

    /**
     * 驾驶证识别API
     */
    public static final String OCR_DRIVER_LICENSE_API = "/ocr/driver-license";

    /**
     * 机动车销售发票识别API
     */
    public static final String OCR_MVS_INVOICE_API = "/ocr/mvs-invoice";
}
