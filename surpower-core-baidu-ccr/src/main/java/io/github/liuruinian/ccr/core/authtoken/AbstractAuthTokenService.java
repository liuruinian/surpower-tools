package io.github.liuruinian.ccr.core.authtoken;

import io.github.liuruinian.ccr.core.exception.AuthTokenEmptyException;
import io.github.liuruinian.ccr.server.properties.BaiduCcrProperties;
import org.springframework.util.StringUtils;

public abstract class AbstractAuthTokenService implements AuthTokenService {

    private final BaiduCcrProperties properties;

    public AbstractAuthTokenService(BaiduCcrProperties properties) {
        this.properties = properties;
    }

    @Override
    public String getAuthToken() {
        preCheck(properties);

        String accessToken = obtainRefreshAccessToken(properties);
        if (!StringUtils.hasLength(accessToken)) {
            throw new AuthTokenEmptyException();
        }

        return accessToken;
    }

    protected abstract String obtainRefreshAccessToken(BaiduCcrProperties properties);

    protected abstract void preCheck(BaiduCcrProperties properties);
}
