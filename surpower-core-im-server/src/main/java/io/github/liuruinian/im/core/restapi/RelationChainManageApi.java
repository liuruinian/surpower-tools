package io.github.liuruinian.im.core.restapi;


import io.github.liuruinian.im.core.params.relationchain.*;
import io.github.liuruinian.im.server.relationchain.*;

/**
 * @author liuruinian
 * @version 2022-02-10
 * <p>
 *     关系链管理API
 * </p>
 */
public interface RelationChainManageApi {

    /**
     * 添加好友，支持批量添加好友。
     *
     * @param friendAddParam 添加好友参数
     */
    String friend_add(FriendAddParam friendAddParam);

    /**
     * 导入好友
     *
     * @param friendImportParam 导入好友参数
     * <p>
     *     1.支持批量导入单向好友.
     *     2.往同一个用户导入好友时建议采用批量导入的方式，避免并发写导致的写冲突.
     * </p>
     */
    String friend_import(FriendImportParam friendImportParam);

    /**
     * 更新好友
     *
     * @param friendUpdateParam 更新好友参数
     * <p>
     *     1.支持批量更新同一用户的多个好友的关系链数据.
     *     2.更新一个用户多个好友时，建议采用批量方式，避免并发写导致的写冲突.
     * </p>
     */
    String friend_update(FriendUpdateParam friendUpdateParam);

    /**
     * 删除好友
     *
     * @param friendDeleteParam 删除好友参数
     * <p>
     *      删除好友，支持单向删除好友和双向删除好友。
     * </p>
     */
    String friend_delete(FriendDeleteParam friendDeleteParam);

    /**
     * 删除所有好友
     *
     * @param friendDeleteAllParam 删除所有好友参数
     * <p>
     *     清除指定用户的标配好友数据和自定义好友数据
     * </p>
     */
    String friend_delete_all(FriendDeleteAllParam friendDeleteAllParam);

    /**
     * 校验好友
     *
     * @param friendCheckParam 检验好友参数
     */
    String friend_check(FriendCheckParam friendCheckParam);

    /**
     * 拉取好友
     *
     * @param friendGetParam 拉取好友参数
     */
    String friend_get(FriendGetParam friendGetParam);

    /**
     * 拉取指定好友的数据和资料
     *
     * @param friendGetListParam 拉取指定好友资料和数据参数
     */
    String friend_get_list(FriendGetListParam friendGetListParam);

    /**
     * 添加黑名单
     *
     * @param blackListAddParam 添加黑名单参数
     */
    String black_list_add(BlackListAddParam blackListAddParam);

    /**
     * 删除黑名单
     *
     * @param blackListDeleteParam 删除黑名单参数
     */
    String black_list_delete(BlackListDeleteParam blackListDeleteParam);

    /**
     * 拉取黑名单
     *
     * @param blackListGetParam 拉取黑名单参数
     */
    String black_list_get(BlackListGetParam blackListGetParam);

    /**
     * 校验黑名单
     *
     * @param blackListCheckParam 校验黑名单参数
     */
    String black_list_check(BlackListCheckParam blackListCheckParam);

    /**
     * 添加分组
     *
     * @param groupAddParam 添加分组参数
     */
    String group_add(GroupAddParam groupAddParam);

    /**
     * 删除分组
     *
     * @param groupDeleteParam 删除分组参数
     */
    String group_delete(GroupDeleteParam groupDeleteParam);

    /**
     * 拉取分组
     *
     * @param groupGetParam 拉取分组参数
     */
    String group_get(GroupGetParam groupGetParam);
}
