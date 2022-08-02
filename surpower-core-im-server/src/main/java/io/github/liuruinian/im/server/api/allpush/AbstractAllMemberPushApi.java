package io.github.liuruinian.im.server.api.allpush;

import io.github.liuruinian.im.core.restapi.AllMemberPushApi;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.api.AbstractImServerApi;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author liuruinian
 * @version 2022-02-10
 * <p>
 *     全员推送API
 * </p>
 */
@Slf4j
public abstract class AbstractAllMemberPushApi extends AbstractImServerApi implements AllMemberPushApi {

    private final ApplicationEventPublisher eventPublisher;

    public AbstractAllMemberPushApi(ImServerProperties imServerProperties,
                                    UserSignService userSignService,
                                    ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService);
        this.eventPublisher = eventPublisher;
    }


}
