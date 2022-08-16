package io.github.liuruinian.ocr.core.restapi.idcard;

import io.github.liuruinian.ocr.core.param.OcrIdCardParam;

/**
 * @author liuruinian
 * <p>
 *     huawei ocr rest api
 * </p>
 */
public interface IdCardOcrApi {

    /**
     * 身份证识别API
     *
     * @param param 身份证识别请求参数
     * @return 识别结果
     */
    String ocrIdCard(OcrIdCardParam param);
}
