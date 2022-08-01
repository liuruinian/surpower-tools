package io.github.liuruinian.im.core.sign;

/**
 * @author liuruinian
 * @version 2021-02-03
 * <p>
 * 服务端用户签名接口
 * </p>
 */
public interface UserSignService {

    /**
     * @param sdkAppId  应用SDKAppID，可在即时通信 IM 控制台 的应用卡片中获取.
     * @param secretKey 密钥信息，可在即时通信 IM 控制台 的应用详情页面中获取.
     * @param userId    用户ID
     * @param expire    UserSig的有效期，单位为秒
     */
    String generateUserSign(Long sdkAppId, String secretKey, String userId, long expire);

    /**
     * 获取用户签名
     *
     * @param userId 用户ID
     */
    String obtainUserSign(String userId);
}
