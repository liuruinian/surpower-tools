package io.github.liuruinian.sms.aliyun.service;

/**
 * verification code interface
 *
 * @author liuruinian
 */
public interface VerificationCodeService {

    // ~ Constants -----------------------------------------------------------

    /**
     * 验证码短信中验证码对应的key
     */
    String MSG_KEY_CODE = "code";

    /**
     * 验证码短信中识别码对应的key
     */
    String MSG_KEY_IDENTIFICATION_CODE = "identificationCode";

    /**
     * 验证码短信中有效期(秒)对应的key
     */
    String MSG_KEY_EXPIRATION_TIME_OF_SECONDS = "expirationTimeOfSeconds";

    /**
     * 验证码短信中有效期(分)对应的key
     */
    String MSG_KEY_EXPIRATION_TIME_OF_MINUTES = "expirationTimeOfMinutes";

    // ~ Methods -------------------------------------------------------------


}