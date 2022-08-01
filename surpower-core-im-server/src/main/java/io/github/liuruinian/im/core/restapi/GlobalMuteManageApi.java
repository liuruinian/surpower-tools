package io.github.liuruinian.im.core.restapi;

import io.github.liuruinian.im.core.params.globalmute.GetNoSpeakingParam;
import io.github.liuruinian.im.core.params.globalmute.SetNoSpeakingParam;

/**
 * 全局禁言管理
 */
public interface GlobalMuteManageApi {

    /**
     * 设置全局禁言
     *
     * @param setNoSpeakingParam 设置全局禁言参数
     */
    String setnospeaking(SetNoSpeakingParam setNoSpeakingParam);

    /**
     * 查询全局禁言
     *
     * @param getNoSpeakingParam 查询全局禁言参数
     */
    String getnospeaking(GetNoSpeakingParam getNoSpeakingParam);
}
