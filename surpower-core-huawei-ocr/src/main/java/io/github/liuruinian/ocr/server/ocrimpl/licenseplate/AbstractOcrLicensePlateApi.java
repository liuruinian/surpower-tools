package io.github.liuruinian.ocr.server.ocrimpl.licenseplate;

import io.github.liuruinian.ocr.core.param.OcrLicensePlateParam;
import io.github.liuruinian.ocr.core.restapi.licenseplate.LicensePlateOcrApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractOcrLicensePlateApi implements LicensePlateOcrApi {

    @Override
    public String ocrLicensePlate(OcrLicensePlateParam param) {
        String responseBody = null;
        try {
            responseBody = handleLicensePlateOcr(param);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractOcrLicensePlateApi::ocrLicensePlate] -> ocr license plate occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleLicensePlateOcr(OcrLicensePlateParam param);
}
