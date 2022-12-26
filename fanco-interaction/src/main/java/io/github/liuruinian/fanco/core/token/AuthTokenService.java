package io.github.liuruinian.fanco.core.token;

/**
 * fanco access token
 *
 * @author liuruinian
 * @version 2022-12-26
 */
public interface AuthTokenService {

    /**
     * get refresh token
     *
     * @return token
     */
    String obtainRefreshToken();
}
