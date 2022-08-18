package io.github.liuruinian.ocr.core.restapi.vin;

import io.github.liuruinian.ocr.core.param.OcrVinParam;

/**
 * @author liuruinian
 * <p>
 *     huawei ocr vin api
 * </p>
 */
public interface VinOcrApi {

    /**
     * VIN码识别API
     *
     * @param param VIN码识别请求参数
     * @return 识别结果
     */
    String ocrVin(OcrVinParam param);
}
