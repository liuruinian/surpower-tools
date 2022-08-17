package io.github.liuruinian.ocr.server.ocrimpl.vehiclelicense;

import io.github.liuruinian.ocr.core.param.OcrVehicleLicenseParam;
import io.github.liuruinian.ocr.core.restapi.vehiclelicense.VehicleLicenseOcrApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractOcrVehicleLicenseApi implements VehicleLicenseOcrApi {

    @Override
    public String ocrVehicleLicense(OcrVehicleLicenseParam param) {
        String responseBody = null;
        try {
            responseBody = handleVehicleLicenseOcr(param);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractOcrVehicleLicenseApi::ocrVehicleLicense] -> ocr vehicle license occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleVehicleLicenseOcr(OcrVehicleLicenseParam param);
}
