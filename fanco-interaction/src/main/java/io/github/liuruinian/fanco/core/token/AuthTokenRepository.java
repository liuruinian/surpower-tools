package io.github.liuruinian.fanco.core.token;

/**
 * token repository
 *
 * @author liuruinian
 * @version 2022-12-26
 */
public interface AuthTokenRepository {

    /**
     * find by key
     *
     * @param key cache key
     * @return token
     */
    String findOne(String key);

    /**
     * save token
     *
     * @param token token
     * @param key   key
     */
    void save(String token, String key);

    /**
     * delete by key
     *
     * @param key key
     */
    void delete(String key);
}
