package io.github.liuruinian.ocr.core.restapi.mvsinvoice;

import io.github.liuruinian.ocr.core.param.OcrMvsInvoiceParam;

/**
 * @author liuruinian
 * <p>
 *     huawei ocr mvs-invoice api
 * </p>
 */
public interface MvsInvoiceOcrApi {

    /**
     * 机动车销售发票识别API
     *
     * @param param 机动车销售发票识别请求参数
     * @return 识别结果
     */
    String ocrMvsInvoice(OcrMvsInvoiceParam param);
}
