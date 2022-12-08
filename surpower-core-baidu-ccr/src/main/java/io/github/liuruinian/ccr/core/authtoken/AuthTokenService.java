package io.github.liuruinian.ccr.core.authtoken;

/**
 * access_token认证接口
 *
 * @author liuruinian
 * @version 2022-12-08
 */
public interface AuthTokenService {

    /**
     * 获取access_token
     *
     * @return access_token
     */
    String getAuthToken();
}
