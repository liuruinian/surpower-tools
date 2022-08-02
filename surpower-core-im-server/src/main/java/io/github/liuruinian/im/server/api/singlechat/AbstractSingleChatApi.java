package io.github.liuruinian.im.server.api.singlechat;

import io.github.liuruinian.im.core.params.singlechat.*;
import io.github.liuruinian.im.core.restapi.SingleChatApi;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.api.AbstractImServerApi;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author liuruinian
 * @version 2022-02-08
 * <p>
 *     抽象单聊API
 * </p>
 */
@Slf4j
public abstract class AbstractSingleChatApi extends AbstractImServerApi implements SingleChatApi {

    private final ApplicationEventPublisher eventPublisher;

    public AbstractSingleChatApi(ImServerProperties imServerProperties,
                                 UserSignService userSignService,
                                 ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService);
        this.eventPublisher = eventPublisher;
    }

    @Override
    public String sendMsg(SingleIssueSingleChatParam singleIssueSingleChatParam) {
        String responseBody = null;

        try {
            responseBody = handleSendMsg(singleIssueSingleChatParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractSingleChatApi] -> send msg occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleSendMsg(SingleIssueSingleChatParam singleIssueSingleChatParam);

    @Override
    public String batchSendMsg(BatchSingleChatParam batchSingleChatParam) {
        String responseBoby = null;

        try {
            responseBoby = handleBatchSendMsg(batchSingleChatParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractSingleChatApi] -> batch send msg occurs error!", e);
            }
        }

        return responseBoby;
    }

    protected abstract String handleBatchSendMsg(BatchSingleChatParam batchSingleChatParam);

    @Override
    public String importMsg(ImportSingleChatParam importSingleChatParam) {
        String responseBoby = null;

        try {
            responseBoby = handleImportMsg(importSingleChatParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractSingleChatApi] -> import msg occurs error!", e);
            }
        }

        return responseBoby;
    }

    protected abstract String handleImportMsg(ImportSingleChatParam importSingleChatParam);

    @Override
    public String admin_getroammsg(QuerySingleChatParam querySingleChatParam) {
        String responseBoby = null;

        try {
            responseBoby = handleAdminGetRoamMsg(querySingleChatParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractSingleChatApi] -> admin_getroammsg occurs error!", e);
            }
        }

        return responseBoby;
    }

    protected abstract String handleAdminGetRoamMsg(QuerySingleChatParam querySingleChatParam);

    @Override
    public String admin_msgwithdraw(WithdrawSingleChatParam withdrawSingleChatParam) {
        String responseBoby = null;

        try {
            responseBoby = handleAdminMsgWithdraw(withdrawSingleChatParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractSingleChatApi] -> admin_msgwithdraw occurs error!", e);
            }
        }

        return responseBoby;
    }

    protected abstract String handleAdminMsgWithdraw(WithdrawSingleChatParam withdrawSingleChatParam);

    @Override
    public String admin_set_msg_read(ReadSingleChatParam readSingleChatParam) {
        String responseBoby = null;

        try {
            responseBoby = handleAdminSetMsgRead(readSingleChatParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractSingleChatApi] -> admin_set_msg_read occurs error!", e);
            }
        }

        return responseBoby;
    }

    protected abstract String handleAdminSetMsgRead(ReadSingleChatParam readSingleChatParam);

    @Override
    public String get_c2c_unread_msg_num(C2CUnreadMsgNumParam c2CUnreadMsgNumParam) {
        String responseBoby = null;

        try {
            responseBoby = handleC2CUnreadMsgNum(c2CUnreadMsgNumParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractSingleChatApi] -> get_c2c_unread_msg_num occurs error!", e);
            }
        }

        return responseBoby;
    }

    protected abstract String handleC2CUnreadMsgNum(C2CUnreadMsgNumParam c2CUnreadMsgNumParam);
}
