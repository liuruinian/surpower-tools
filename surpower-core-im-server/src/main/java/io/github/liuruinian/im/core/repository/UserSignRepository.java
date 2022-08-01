package io.github.liuruinian.im.core.repository;
/**
 * @author liuruinian
 * @version 2022-02-03
 * <p>
 *     用户签名存储接口
 * </p>
 */
public interface UserSignRepository {

    /**
     * 根据唯一键获取UserSign
     *
     * @param uniqueKey 唯一键
     */
    String findOne(String uniqueKey);

    /**
     * 存储UserSign
     *
     * @param userSign  用户签名
     * @param userId    用户ID
     */
    void save(String userSign, String userId);

    /**
     * 根据唯一键删除UserSign
     */
    void delete(String uniqueKey);
}
