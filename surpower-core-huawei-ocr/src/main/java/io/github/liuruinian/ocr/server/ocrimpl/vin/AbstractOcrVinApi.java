package io.github.liuruinian.ocr.server.ocrimpl.vin;

import io.github.liuruinian.ocr.core.param.OcrVinParam;
import io.github.liuruinian.ocr.core.restapi.vin.VinOcrApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractOcrVinApi implements VinOcrApi {

    @Override
    public String ocrVin(OcrVinParam param) {
        String responseBody = null;
        try {
            responseBody = handleVinOcr(param);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractOcrVinApi::ocrVin] -> ocr vin occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleVinOcr(OcrVinParam param);
}
