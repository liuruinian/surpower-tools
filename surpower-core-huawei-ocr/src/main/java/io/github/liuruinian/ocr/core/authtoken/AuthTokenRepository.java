package io.github.liuruinian.ocr.core.authtoken;

/**
 * @author liuruinian
 * @version 2022-8-16
 * <p>
 *      Token认证存储接口
 * </p>
 */
public interface AuthTokenRepository {

    /**
     * 根据唯一键获取AuthToken
     *
     * @param key 唯一键
     */
    String findOne(String key);

    /**
     * 存储AuthToken
     *
     * @param authToken AuthToken
     * @param key       key
     */
    void save(String authToken, String key);

    /**
     * 根据唯一键删除AuthToken
     */
    void delete(String key);
}
