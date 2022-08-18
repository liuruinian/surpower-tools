package io.github.liuruinian.ocr.server.ocrimpl.bankcard;

import io.github.liuruinian.ocr.core.param.OcrBankCardParam;
import io.github.liuruinian.ocr.core.restapi.bankcard.BankCardOcrApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractOcrBankCardApi implements BankCardOcrApi {

    @Override
    public String ocrBankCard(OcrBankCardParam param) {
        String responseBody = null;
        try {
            responseBody = handleBankCardOcr(param);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractOcrBankCardApi::ocrBankCard] -> ocr bank card occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleBankCardOcr(OcrBankCardParam param);
}
