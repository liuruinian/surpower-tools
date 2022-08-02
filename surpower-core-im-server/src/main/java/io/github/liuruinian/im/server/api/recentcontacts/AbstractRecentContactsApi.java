package io.github.liuruinian.im.server.api.recentcontacts;

import io.github.liuruinian.im.core.params.recentcontacts.DeleteSessionParam;
import io.github.liuruinian.im.core.params.recentcontacts.GetListParam;
import io.github.liuruinian.im.core.restapi.RecentContactsApi;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.api.AbstractImServerApi;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public abstract class AbstractRecentContactsApi extends AbstractImServerApi implements RecentContactsApi {

    private final ApplicationEventPublisher eventPublisher;

    public AbstractRecentContactsApi(ImServerProperties imServerProperties,
                                     UserSignService userSignService,
                                     ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService);
        this.eventPublisher = eventPublisher;
    }

    @Override
    public String get_list(GetListParam getListParam) {
        String responseBody = null;

        try {
            responseBody = handleGetList(getListParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRecentContactsApi] -> get_list occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleGetList(GetListParam getListParam);

    @Override
    public String delete(DeleteSessionParam deleteSessionParam) {
        String responseBody = null;

        try {
            responseBody = handleDeleteSession(deleteSessionParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRecentContactsApi] -> delete session occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleDeleteSession(DeleteSessionParam deleteSessionParam);


}
