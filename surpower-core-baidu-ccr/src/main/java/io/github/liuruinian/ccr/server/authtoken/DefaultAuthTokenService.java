package io.github.liuruinian.ccr.server.authtoken;

import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.ccr.core.authtoken.AbstractAuthTokenService;
import io.github.liuruinian.ccr.core.authtoken.AuthTokenRepository;
import io.github.liuruinian.ccr.core.exception.AuthTokenEmptyException;
import io.github.liuruinian.ccr.core.exception.InvalidAuthTokenUrlException;
import io.github.liuruinian.ccr.server.properties.BaiduCcrProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

@Slf4j
public class DefaultAuthTokenService extends AbstractAuthTokenService {

    private final AuthTokenRepository repository;

    public DefaultAuthTokenService(BaiduCcrProperties properties, AuthTokenRepository repository) {
        super(properties);
        this.repository = repository;
    }

    @Override
    protected String obtainRefreshAccessToken(BaiduCcrProperties properties) {
        String accessToken = repository.findOne(properties.getApiKey());
        if (StringUtils.hasLength(accessToken)) {
            return accessToken;
        }

        String s = properties.getTokenUri() + "?grant_type=client_credentials&client_id=%s&client_secret=%s";
        String tokenUri = String.format(s, properties.getApiKey(), properties.getSecretKey());

        HttpResponse response = HttpUtil.createPost(tokenUri)
                .header(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .execute();

        if (response.isOk()) {
            if (log.isInfoEnabled()) {
                log.info("[DefaultAuthTokenService] -> Token Auth Http Request Success!");
            }

            JSONObject body = JSONObject.parseObject(response.body());
            accessToken = body.getString("access_token");
            if (!StringUtils.hasLength(accessToken)) {
                throw new AuthTokenEmptyException();
            }

            repository.save(accessToken, properties.getApiKey());

            return accessToken;
        }

        return null;
    }

    @Override
    protected void preCheck(BaiduCcrProperties properties) {
        if (!StringUtils.hasLength(properties.getTokenUri())) {
            throw new InvalidAuthTokenUrlException();
        }
    }
}
