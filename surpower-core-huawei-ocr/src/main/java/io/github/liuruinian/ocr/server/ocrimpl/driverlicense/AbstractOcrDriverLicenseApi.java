package io.github.liuruinian.ocr.server.ocrimpl.driverlicense;

import io.github.liuruinian.ocr.core.param.OcrDriverLicenseParam;
import io.github.liuruinian.ocr.core.restapi.driverlicense.DriverLicenseOcrApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractOcrDriverLicenseApi implements DriverLicenseOcrApi {

    @Override
    public String ocrDriverLicense(OcrDriverLicenseParam param) {
        String responseBody = null;
        try {
            responseBody = handleDriverLicenseOcr(param);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractOcrDriverLicenseApi::ocrDriverLicense] -> ocr driver license occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleDriverLicenseOcr(OcrDriverLicenseParam param);
}
