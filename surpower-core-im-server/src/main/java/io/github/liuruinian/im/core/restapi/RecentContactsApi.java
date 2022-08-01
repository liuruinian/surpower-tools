package io.github.liuruinian.im.core.restapi;

import io.github.liuruinian.im.core.params.recentcontacts.DeleteSessionParam;
import io.github.liuruinian.im.core.params.recentcontacts.GetListParam;

/**
 * @author liuruinian
 * @version 2022-02-14
 * <p>
 *     最近联系人API
 * </p>
 */
public interface RecentContactsApi {

    /**
     * 拉取会话列表, 支持分页拉取会话列表。
     *
     * @param getListParam 拉取会话列表参数
     */
    String get_list(GetListParam getListParam);

    /**
     * 删除单个会话
     *
     * @param deleteSessionParam 删除会话参数
     */
    String delete(DeleteSessionParam deleteSessionParam);
}
