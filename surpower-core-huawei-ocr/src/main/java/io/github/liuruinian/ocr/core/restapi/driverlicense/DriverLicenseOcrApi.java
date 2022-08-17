package io.github.liuruinian.ocr.core.restapi.driverlicense;

import io.github.liuruinian.ocr.core.param.OcrDriverLicenseParam;

/**
 * @author liuruinian
 * <p>
 *     huawei ocr driver-license api
 * </p>
 */
public interface DriverLicenseOcrApi {

    /**
     * 驾驶证识别API
     *
     * @param param 驾驶证识别请求参数
     * @return 识别结果
     */
    String ocrDriverLicense(OcrDriverLicenseParam param);
}
