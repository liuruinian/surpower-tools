package io.github.liuruinian.ccr.core.authtoken;

/**
 * AccessToken持久化
 *
 * @author liuruinian
 * @version 2022-12-08
 */
public interface AuthTokenRepository {

    /**
     * 根据唯一键获取AccessToken
     *
     * @param key 唯一键
     */
    String findOne(String key);

    /**
     * 存储AccessToken
     *
     * @param accessToken access_token
     * @param key       key
     */
    void save(String accessToken, String key);

    /**
     * 根据唯一键删除AccessToken
     */
    void delete(String key);
}
