package io.github.liuruinian.ocr.server.ocrimpl.idcard;

import io.github.liuruinian.ocr.core.param.OcrIdCardParam;
import io.github.liuruinian.ocr.core.restapi.idcard.IdCardOcrApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractOcrIdCardApi implements IdCardOcrApi {

    @Override
    public String ocrIdCard(OcrIdCardParam param) {
        String responseBody = null;
        try {
            responseBody = handleIdCardOcr(param);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractOcrIdCard::ocrIdCard] -> ocr id card occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleIdCardOcr(OcrIdCardParam param);
}
