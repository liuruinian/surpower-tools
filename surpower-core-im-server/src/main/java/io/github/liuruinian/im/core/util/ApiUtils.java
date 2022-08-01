package io.github.liuruinian.im.core.util;

import io.github.liuruinian.im.core.constant.TencentCloudImApiConstant;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author liuruinian
 * @version 2022-02-06
 * <p>
 *     Api工具类
 * </p>
 */
public class ApiUtils {

    /**
     * 获取请求地址
     *
     * @param imServiceApi  IM服务API名称
     * @param sdkAppId      SDK应用AppID
     * @param identifier    必须为 App 管理员帐号
     * @param userSign      App 管理员帐号生成的签名
     * @param random        请输入随机的32位无符号整数，取值范围0 - 4294967295
     */
    public static String obtainRequestUrl(String imServiceApi,
                                          Long sdkAppId,
                                          String identifier,
                                          String userSign,
                                          Long random) {
        return String.format("%s%s?sdkappid=%s&identifier=%s&usersig=%s&random=%s&contenttype=json",
                TencentCloudImApiConstant.HTTPS_URL_PREFIX, imServiceApi, sdkAppId, identifier, userSign, random);
    }

    /**
     * 获取随机请求ID
     */
    public static Long randomRequestId() {
        ThreadLocalRandom random = current();
        return random.nextLong(0, 4294967296L);
    }

    /**
     * 获取MsgRandom
     */
    public static Integer  msgRandom() {
        ThreadLocalRandom random = current();
        return random.nextInt(0, Integer.MAX_VALUE);
    }

    /**
     * 消息时间戳，UNIX 时间戳（单位：秒）
     */
    public static Integer unixTimeStamp() {
        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
        LocalDateTime localDateTime = LocalDateTime.now();
        return (int) localDateTime.toEpochSecond(zoneOffset);
    }
}
