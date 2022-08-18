package io.github.liuruinian.ocr.core.restapi.generalword;

import io.github.liuruinian.ocr.core.param.OcrGeneralTextParam;
import io.github.liuruinian.ocr.core.param.OcrIdCardParam;

/**
 * 通用文字识别
 *
 * @author admin
 */
public interface GeneralTextApi {

    /**
     * 识别图片上的文字信息，以JSON格式返回识别的文字和坐标。支持扫描文件、电子文档、书籍、票据和表单等多种场景的文字识别。
     *
     * @param param
     * @return
     */
    String ocrGeneralText(OcrGeneralTextParam param);
}
