package io.github.liuruinian.ocr.core.restapi.licenseplate;

import io.github.liuruinian.ocr.core.param.OcrLicensePlateParam;

/**
 * @author liuruinian
 * <p>
 *     huawei ocr license-plate api
 * </p>
 */
public interface LicensePlateOcrApi {

    /**
     * 车牌识别API
     *
     * @param param 车牌识别请求参数
     * @return 识别结果
     */
    String ocrLicensePlate(OcrLicensePlateParam param);
}
