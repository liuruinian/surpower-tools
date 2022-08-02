package io.github.liuruinian.im.server.api.operationmanage;

import io.github.liuruinian.im.core.params.operationmanage.GetAppInfoParam;
import io.github.liuruinian.im.core.params.operationmanage.GetHistoryMsgParam;
import io.github.liuruinian.im.core.restapi.OperationManageApi;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.api.AbstractImServerApi;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public abstract class AbstractOperationManageApi extends AbstractImServerApi implements OperationManageApi {

    private final ApplicationEventPublisher eventPublisher;

    public AbstractOperationManageApi(ImServerProperties imServerProperties,
                                      UserSignService userSignService,
                                      ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService);
        this.eventPublisher = eventPublisher;
    }

    @Override
    public String getappinfo(GetAppInfoParam getAppInfoParam) {
        String responseBody = null;

        try {
            responseBody = handleAppInfo(getAppInfoParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractOperationManageApi] -> getappinfo occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleAppInfo(GetAppInfoParam getAppInfoParam);

    @Override
    public String get_history(GetHistoryMsgParam getHistoryMsgParam) {
        String responseBody = null;

        try {
            responseBody = handleHistoryMsg(getHistoryMsgParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractOperationManageApi] -> get_history occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleHistoryMsg(GetHistoryMsgParam getHistoryMsgParam);

    @Override
    public String getIpList() {
        String responseBody = null;

        try {
            responseBody = handleIpList();
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractOperationManageApi] -> getIpList occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleIpList();
}
