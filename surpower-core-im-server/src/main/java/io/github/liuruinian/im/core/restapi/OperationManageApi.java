package io.github.liuruinian.im.core.restapi;

import io.github.liuruinian.im.core.params.operationmanage.GetAppInfoParam;
import io.github.liuruinian.im.core.params.operationmanage.GetHistoryMsgParam;

/**
 * 运营管理API
 */
public interface OperationManageApi {

    /**
     * 拉取运营数据
     *
     * @param getAppInfoParam 拉取运营数据参数
     */
    String getappinfo(GetAppInfoParam getAppInfoParam);

    /**
     * 下载最近消息记录
     *
     * @param getHistoryMsgParam 下载最近消息记录参数
     */
    String get_history(GetHistoryMsgParam getHistoryMsgParam);

    /**
     * 获取服务器IP地址
     */
    String getIpList();
}
