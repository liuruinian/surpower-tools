package io.github.liuruinian.ocr.core.util;

import io.github.liuruinian.ocr.server.properties.HuaweiOcrProperties;

/**
 * @author liuruinian
 * @version 2022-08-16
 * <p>
 *      Api工具类
 * </p>
 */
public class HuaweiOcrApiUtil {

    /**
     * 获取请求地址
     * 格式：https://ocr.cn-north-4.myhuaweicloud.com/v2/{project_id}/{api_name}
     * @param serverApi  服务API名称
     * @param properties 华为云OCR相关配置参数
     */
    public static String obtainRequestUri(String serverApi, HuaweiOcrProperties properties) {
        return String.format("https://%s/v2/%s%s", properties.getEndpoint(), properties.getProjectId(), serverApi);
    }
}