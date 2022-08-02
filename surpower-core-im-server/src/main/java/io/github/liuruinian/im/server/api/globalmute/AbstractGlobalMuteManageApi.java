package io.github.liuruinian.im.server.api.globalmute;

import io.github.liuruinian.im.core.params.globalmute.GetNoSpeakingParam;
import io.github.liuruinian.im.core.params.globalmute.SetNoSpeakingParam;
import io.github.liuruinian.im.core.restapi.GlobalMuteManageApi;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.api.AbstractImServerApi;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public abstract class AbstractGlobalMuteManageApi extends AbstractImServerApi implements GlobalMuteManageApi {

    private final ApplicationEventPublisher eventPublisher;

    public AbstractGlobalMuteManageApi(ImServerProperties imServerProperties,
                                       UserSignService userSignService,
                                       ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService);
        this.eventPublisher = eventPublisher;
    }

    @Override
    public String setnospeaking(SetNoSpeakingParam setNoSpeakingParam) {
        String responseBody = null;

        try {
            responseBody = handleSetNoSpeaking(setNoSpeakingParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractGlobalMuteManageApi] -> setnospeaking occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleSetNoSpeaking(SetNoSpeakingParam setNoSpeakingParam);

    @Override
    public String getnospeaking(GetNoSpeakingParam getNoSpeakingParam) {
        String responseBody = null;

        try {
            responseBody = handleGetNoSpeaking(getNoSpeakingParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractGlobalMuteManageApi] -> getnospeaking occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleGetNoSpeaking(GetNoSpeakingParam getNoSpeakingParam);


}
