package io.github.liuruinian.ocr.core.restapi.vehiclelicense;

import io.github.liuruinian.ocr.core.param.OcrVehicleLicenseParam;

/**
 * @author liuruinian
 * <p>
 *     huawei ocr vehicle-license api
 * </p>
 */
public interface VehicleLicenseOcrApi {
    /**
     * 行驶证识别API
     *
     * @param param 行驶证识别请求参数
     * @return 识别结果
     */
    String ocrVehicleLicense(OcrVehicleLicenseParam param);
}
