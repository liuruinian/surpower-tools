package io.github.liuruinian.fanco.core.token;

import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.fanco.core.exception.AccessTokenUriEmptyException;
import io.github.liuruinian.fanco.core.exception.ClientIdEmptyException;
import io.github.liuruinian.fanco.core.exception.ClientSecretEmptyException;
import io.github.liuruinian.fanco.server.properties.FancoProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

public abstract class AbstractAuthTokenService implements AuthTokenService, InitializingBean {

    private final FancoProperties properties;

    private final AuthTokenRepository repository;

    public AbstractAuthTokenService(FancoProperties properties, AuthTokenRepository repository) {
        this.properties = properties;
        this.repository = repository;
    }

    @Override
    public String obtainRefreshToken() {
        String token = repository.findOne(properties.getClientId());
        if (StringUtils.hasLength(token)) {
            return token;
        }

        String accessTokenUri = properties.getAccessTokenUri();
        HttpResponse response = HttpUtil.createPost(accessTokenUri)
                .header(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(Header.AUTHORIZATION, "Basic " + encryptClientIdSecret())
                .execute();

        if (response.isOk()) {
            JSONObject json = JSONObject.parseObject(response.body());
            if (json != null) {
                repository.save(json.toJSONString(), properties.getClientId());
                return json.toJSONString();
            }
        }

        return "";
    }

    private String encryptClientIdSecret() {
        String clientId = properties.getClientId();
        String clientSecret = properties.getClientSecret();
        return Base64Utils.encodeToString((clientId + ":" + clientSecret).getBytes());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String accessTokenUri = properties.getAccessTokenUri();
        String clientId = properties.getClientId();
        String clientSecret = properties.getClientSecret();

        if (!StringUtils.hasLength(accessTokenUri)) {
            throw new AccessTokenUriEmptyException();
        }

        if (!StringUtils.hasLength(clientId)) {
            throw new ClientIdEmptyException();
        }

        if (!StringUtils.hasLength(clientSecret)) {
            throw new ClientSecretEmptyException();
        }
    }
}
