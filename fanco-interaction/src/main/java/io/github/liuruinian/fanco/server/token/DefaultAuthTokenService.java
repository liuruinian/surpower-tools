package io.github.liuruinian.fanco.server.token;

import io.github.liuruinian.fanco.core.token.AbstractAuthTokenService;
import io.github.liuruinian.fanco.core.token.AuthTokenRepository;
import io.github.liuruinian.fanco.server.properties.FancoProperties;

public class DefaultAuthTokenService extends AbstractAuthTokenService {

    public DefaultAuthTokenService(FancoProperties properties, AuthTokenRepository repository) {
        super(properties, repository);
    }
}
