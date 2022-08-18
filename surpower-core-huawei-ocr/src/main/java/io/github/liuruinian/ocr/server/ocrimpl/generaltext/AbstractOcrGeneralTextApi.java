package io.github.liuruinian.ocr.server.ocrimpl.generaltext;

import io.github.liuruinian.ocr.core.param.OcrGeneralTextParam;
import io.github.liuruinian.ocr.core.param.OcrIdCardParam;
import io.github.liuruinian.ocr.core.restapi.generalword.GeneralTextApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public abstract class AbstractOcrGeneralTextApi implements GeneralTextApi {

    @Override
    public String ocrGeneralText(OcrGeneralTextParam param) {
        String responseBody = null;
        try {
            responseBody = handleGeneralTextOcr(param);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractOcrGeneralTextApi::generalTextApi] -> ocr general text occurs error!", e);
            }
        }
        return responseBody;
    }

    protected abstract String handleGeneralTextOcr(OcrGeneralTextParam param);
}
