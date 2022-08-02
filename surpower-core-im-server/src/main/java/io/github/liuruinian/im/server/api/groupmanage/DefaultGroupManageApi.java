package io.github.liuruinian.im.server.api.groupmanage;

import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public class DefaultGroupManageApi extends AbstractGroupManageApi {

    public DefaultGroupManageApi(ImServerProperties imServerProperties,
                                 UserSignService userSignService,
                                 ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService, eventPublisher);
    }


}
