package io.github.liuruinian.ocr.core.authtoken;

import io.github.liuruinian.ocr.core.exception.AuthTokenEmptyException;
import io.github.liuruinian.ocr.core.util.StringUtils;
import io.github.liuruinian.ocr.server.properties.HuaweiOcrProperties;
import org.springframework.util.Assert;

public abstract class AbstractAuthTokenService implements AuthTokenService {

    private final HuaweiOcrProperties properties;

    public AbstractAuthTokenService(HuaweiOcrProperties properties) {
        this.properties = properties;
    }

    @Override
    public String obtainRefreshableAuthToken() {
        Assert.notNull(properties, "Configuration in HuaweiOcrProperties not allow empty!");
        preCheck(properties);
        String authToken = obtainCachedAuthToken(properties);
        if (StringUtils.isBlank(authToken)) {
            throw new AuthTokenEmptyException();
        }

        return authToken;
    }

    protected abstract String obtainCachedAuthToken(HuaweiOcrProperties properties);

    protected abstract void preCheck(HuaweiOcrProperties properties);
}
