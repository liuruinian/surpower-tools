package io.github.liuruinian.im.core.restapi;

import java.util.List;
import java.util.Set;

/**
 * @author liuruinian
 * @version 2022-02-06
 * <p>
 *     账号管理API
 * </p>
 */
public interface AccountManageApi {

    /**
     * 导入单个账号
     *
     * @param userId    用户名，长度不超过32字节 必填
     * @param nick      用户昵称 选填
     * @param faceUrl   用户头像URL 选填
     * <p>
     *     1.本接口用于将 App 自有帐号导入即时通信 IM 帐号系统，为该帐号创建一个对应的内部 ID，使该帐号能够使用即时通信 IM 服务。
     *     2.该接口是幂等性的，即：同一个帐号重复导入仅会创建1个内部 ID。
     * </p>
     */
     String accountImport(String userId, String nick, String faceUrl);

     default String accountImport(String userId, String nick) {
         return accountImport(userId, nick, null);
     }

     default String accountImport(String userId) {
         return accountImport(userId, null, null);
     }

     /**
      * 导入多个账号
      *
      * @param accounts 用户名集合
      * <p>
      *     用户名，单个用户名长度不超过32字节，单次最多导入100个用户名
      * </p>
      */
     String multiAccountImport(List<String> accounts);

     /**
      * 删除账号
      *
      * @param userIds 用户名集合
      * <p>
      *     1.仅支持删除套餐包类型为 IM 体验版的帐号，其他类型的帐号（如：TRTC、白板、专业版、旗舰版）无法删除.
      *     2.帐号删除时，该用户的关系链、资料等数据也会被删除.
      *     3.帐号删除后，该用户的数据将无法恢复，请谨慎使用该接口.
      * </p>
      */
     String accountDelete(Set<String> userIds);

     /**
      * 检查账号是否导入IM
      *
      * @param userIds 用户名集合
      * <p>
      *     用于查询自有帐号是否已导入即时通信 IM，支持批量查询.
      * </p>
      */
     String accountCheckImport(Set<String> userIds);

     /**
      * 失效账号登录状态
      *
      * @param userId 用户名
      * <p>
      *     本接口适用于将 App 用户帐号的登录状态（例如 UserSig）失效.
      * </p>
      */
     String kick(String userId);

     /**
      * 获取用户当前的登录状态
      *
      * @param userIds      用户ID集合, 一次最多查询500个 UserID 的状态.
      * @param isNeedDetail 是否需要返回详细的登录平台信息。0表示不需要，1表示需要
      */
     String queryOnlineStatus(Set<String> userIds, Integer isNeedDetail);
}
