package io.github.liuruinian.im.server.api.groupmanage;

import io.github.liuruinian.im.core.restapi.GroupManageApi;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.api.AbstractImServerApi;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public abstract class AbstractGroupManageApi extends AbstractImServerApi implements GroupManageApi {

    private final ApplicationEventPublisher eventPublisher;

    public AbstractGroupManageApi(ImServerProperties imServerProperties,
                                  UserSignService userSignService,
                                  ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService);
        this.eventPublisher = eventPublisher;
    }


}
