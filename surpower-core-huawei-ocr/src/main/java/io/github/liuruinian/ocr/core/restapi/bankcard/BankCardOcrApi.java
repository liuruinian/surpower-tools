package io.github.liuruinian.ocr.core.restapi.bankcard;

import io.github.liuruinian.ocr.core.param.OcrBankCardParam;

/**
 * @author liuruinian
 * <p>
 *     huawei ocr bank card api
 * </p>
 */
public interface BankCardOcrApi {

    /**
     * 银行卡识别API
     *
     * @param param 银行卡识别请求参数
     * @return 识别结果
     */
    String ocrBankCard(OcrBankCardParam param);
}
