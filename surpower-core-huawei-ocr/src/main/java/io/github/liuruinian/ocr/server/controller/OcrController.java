package io.github.liuruinian.ocr.server.controller;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.ocr.core.param.OcrDriverLicenseParam;
import io.github.liuruinian.ocr.core.param.OcrGeneralTextParam;
import io.github.liuruinian.ocr.core.param.OcrIdCardParam;
import io.github.liuruinian.ocr.core.param.OcrVehicleLicenseParam;
import io.github.liuruinian.ocr.core.param.*;
import io.github.liuruinian.ocr.core.restapi.bankcard.BankCardOcrApi;
import io.github.liuruinian.ocr.core.restapi.driverlicense.DriverLicenseOcrApi;
import io.github.liuruinian.ocr.core.restapi.generalword.GeneralTextApi;
import io.github.liuruinian.ocr.core.restapi.idcard.IdCardOcrApi;
import io.github.liuruinian.ocr.core.restapi.licenseplate.LicensePlateOcrApi;
import io.github.liuruinian.ocr.core.restapi.mvsinvoice.MvsInvoiceOcrApi;
import io.github.liuruinian.ocr.core.restapi.vehiclelicense.VehicleLicenseOcrApi;
import io.github.liuruinian.ocr.core.restapi.vin.VinOcrApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

@RestController
@Slf4j
@Api(tags = "OCR识别API")
public class OcrController {

    private IdCardOcrApi idCardOcrApi;

    private VehicleLicenseOcrApi vehicleLicenseOcrApi;

    private DriverLicenseOcrApi  driverLicenseOcrApi;

    private MvsInvoiceOcrApi     mvsInvoiceOcrApi;

    private BankCardOcrApi       bankCardOcrApi;

    private LicensePlateOcrApi   licensePlateOcrApi;

    private VinOcrApi            vinOcrApi;

    @Autowired
    public void setVinOcrApi(VinOcrApi vinOcrApi) {
        this.vinOcrApi = vinOcrApi;
    }

    @Autowired
    public void setLicensePlateOcrApi(LicensePlateOcrApi licensePlateOcrApi) {
        this.licensePlateOcrApi = licensePlateOcrApi;
    }

    @Autowired
    public void setBankCardOcrApi(BankCardOcrApi bankCardOcrApi) {
        this.bankCardOcrApi = bankCardOcrApi;
    }

    @Autowired
    public void setMvsInvoiceOcrApi(MvsInvoiceOcrApi mvsInvoiceOcrApi) {
        this.mvsInvoiceOcrApi = mvsInvoiceOcrApi;
    }

    private GeneralTextApi generalTextApi;

    @Autowired
    public void setGeneralTextApi(GeneralTextApi generalTextApi) {
        this.generalTextApi = generalTextApi;
    }

    @Autowired
    public void setDriverLicenseOcrApi(DriverLicenseOcrApi driverLicenseOcrApi) {
        this.driverLicenseOcrApi = driverLicenseOcrApi;
    }

    @Autowired
    public void setVehicleLicenseOcrApi(VehicleLicenseOcrApi vehicleLicenseOcrApi) {
        this.vehicleLicenseOcrApi = vehicleLicenseOcrApi;
    }

    @Autowired
    public void setIdCardOcrApi(IdCardOcrApi idCardOcrApi) {
        this.idCardOcrApi = idCardOcrApi;
    }

    @ApiOperation("将图片转成Base64编码")
    public JSONObject transferImgToBase64(@RequestPart(value = "file") MultipartFile file) {
        try {
            if (isImage(file.getInputStream())) {
                throw new RuntimeException("当前文件不是图片类型!");
            }

            byte[] bytes = file.getBytes();
            String encode = Base64.encode(bytes);

            JSONObject result = new JSONObject();
            result.put("code", HttpStatus.OK.value());
            result.put("data", encode);

            return result;
        } catch (Exception e) {
            log.error("将图片转成Base64编码异常!", e);
            throw new RuntimeException(e);
        }
    }

    public boolean isImage(InputStream stream) {
        try {
            ImageIO.read(stream);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @ApiOperation("身份证识别")
    public JSONObject ocrIdCard(@RequestBody OcrIdCardParam param) {
        try {
            String responseBody = idCardOcrApi.ocrIdCard(param);
            return JSONObject.parseObject(responseBody);
        } catch (Exception e) {
            log.error("身份证识别异常!", e);
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("行驶证识别")
    public JSONObject ocrVehicleLicense(@RequestBody OcrVehicleLicenseParam param) {
        try {
            String responseBody = vehicleLicenseOcrApi.ocrVehicleLicense(param);
            return JSONObject.parseObject(responseBody);
        } catch (Exception e) {
            log.error("行驶证识别异常!", e);
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("驾驶证识别")
    public JSONObject ocrDriverLicense(@RequestBody OcrDriverLicenseParam param) {
        try {
            String responseBody = driverLicenseOcrApi.ocrDriverLicense(param);
            return JSONObject.parseObject(responseBody);
        } catch (Exception e) {
            log.error("驾驶证识别异常!", e);
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("机动车销售发票识别")
    public JSONObject ocrMvsInvoice(@RequestBody OcrMvsInvoiceParam param) {
        try {
            String responseBody = mvsInvoiceOcrApi.ocrMvsInvoice(param);
            return JSONObject.parseObject(responseBody);
        } catch (Exception e) {
            log.error("机动车销售发票识别异常!", e);
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("银行卡识别")
    public JSONObject ocrBankCard(@RequestBody OcrBankCardParam param) {
        try {
            String responseBody = bankCardOcrApi.ocrBankCard(param);
            return JSONObject.parseObject(responseBody);
        } catch (Exception e) {
            log.error("银行卡识别异常!", e);
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("通用文本识别 ")
    public JSONObject ocrGeneralText(@RequestBody OcrGeneralTextParam param) {
        try {
            String responseBody = generalTextApi.ocrGeneralText(param);
            return JSONObject.parseObject(responseBody);
        } catch (Exception e) {
            log.error("通用文本识别异常!", e);
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("车牌识别")
    public JSONObject ocrLicensePlate(@RequestBody OcrLicensePlateParam param) {
        try {
            String responseBody = licensePlateOcrApi.ocrLicensePlate(param);
            return JSONObject.parseObject(responseBody);
        } catch (Exception e) {
            log.error("车牌识别异常!", e);
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("VIN码识别")
    public JSONObject ocrVin(@RequestBody OcrVinParam param) {
        try {
            String responseBody = vinOcrApi.ocrVin(param);
            return JSONObject.parseObject(responseBody);
        } catch (Exception e) {
            log.error("VIN码识别异常!", e);
            throw new RuntimeException(e);
        }
    }
}
