package io.github.liuruinian.im.server.api.portrait;

import io.github.liuruinian.im.core.params.portrait.PortraitGetParam;
import io.github.liuruinian.im.core.params.portrait.PortraitSetParam;
import io.github.liuruinian.im.core.restapi.PortraitManageApi;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.api.AbstractImServerApi;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author liuruinian
 * @version 2022-02-10
 * <p>
 *     资料设置API
 * </p>
 */
@Slf4j
public abstract class AbstractPortraitManageApi extends AbstractImServerApi implements PortraitManageApi {

    private final ApplicationEventPublisher eventPublisher;

    public AbstractPortraitManageApi(ImServerProperties imServerProperties,
                                     UserSignService userSignService,
                                     ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService);
        this.eventPublisher = eventPublisher;
    }

    @Override
    public String portrait_set(PortraitSetParam portraitSetParam) {
        String responseBody = null;

        try {
            responseBody = handlePortraitSet(portraitSetParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractPortraitManageApi] -> portrait_set occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handlePortraitSet(PortraitSetParam portraitSetParam);

    @Override
    public String portrait_get(PortraitGetParam portraitGetParam) {
        String responseBody = null;

        try {
            responseBody = handlePortraitGet(portraitGetParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractPortraitManageApi] -> portrait_get occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handlePortraitGet(PortraitGetParam portraitGetParam);


}
