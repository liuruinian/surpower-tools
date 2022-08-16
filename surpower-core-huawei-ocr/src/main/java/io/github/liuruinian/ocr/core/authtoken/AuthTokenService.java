package io.github.liuruinian.ocr.core.authtoken;

/**
 * @author liuruinian
 * @version 2021-08-16
 * <p>
 *      Token认证接口
 * </p>
 */
public interface AuthTokenService {

    /**
     * <p>
     *      1.Token的有效期为24小时，需要使用一个Token鉴权时，可以先缓存起来，避免频繁调用。
     *      2.如果您的华为云帐号已升级为华为帐号，将不支持获取帐号Token。建议为您自己创建一个IAM用户，获取IAM用户的Token.
     * </p>
     * 获取最新AuthToken
     * @return x-auth-token
     */
    String obtainRefreshableAuthToken();
}
