package io.github.liuruinian.ocr.server.ocrimpl.mvsinvoice;

import io.github.liuruinian.ocr.core.param.OcrMvsInvoiceParam;
import io.github.liuruinian.ocr.core.restapi.mvsinvoice.MvsInvoiceOcrApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractOcrMvsInvoiceApi implements MvsInvoiceOcrApi {

    @Override
    public String ocrMvsInvoice(OcrMvsInvoiceParam param) {
        String responseBody = null;
        try {
            responseBody = handleMvsInvoiceOcr(param);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractOcrMvsInvoiceApi::ocrMvsInvoice] -> ocr mvs invoice occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleMvsInvoiceOcr(OcrMvsInvoiceParam param);
}
