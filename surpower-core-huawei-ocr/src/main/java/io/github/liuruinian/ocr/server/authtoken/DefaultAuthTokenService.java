package io.github.liuruinian.ocr.server.authtoken;

import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.ocr.core.authtoken.AbstractAuthTokenService;
import io.github.liuruinian.ocr.core.authtoken.AuthTokenRepository;
import io.github.liuruinian.ocr.core.constant.HuaweiCloudOcrApiConstant;
import io.github.liuruinian.ocr.core.exception.InvalidAuthTokenUrlException;
import io.github.liuruinian.ocr.core.exception.XSubjectTokenEmptyException;
import io.github.liuruinian.ocr.core.util.StringUtils;
import io.github.liuruinian.ocr.server.properties.HuaweiOcrProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.util.Collections;

@Slf4j
public class DefaultAuthTokenService extends AbstractAuthTokenService {

    private final AuthTokenRepository repository;

    private final HuaweiOcrProperties properties;

    public DefaultAuthTokenService(HuaweiOcrProperties properties, AuthTokenRepository repository) {
        super(properties);
        this.repository = repository;
        this.properties = properties;
    }

    @Override
    protected String obtainCachedAuthToken(HuaweiOcrProperties properties) {
        String cacheAuthToken = repository.findOne(properties.getUsername());

        if (StringUtils.isNotBlank(cacheAuthToken)) {
            return cacheAuthToken;
        }

        String authTokenUrl = properties.getAuthTokenUrl();
        String authTokenApi = HuaweiCloudOcrApiConstant.X_AUTH_TOKEN_API;

        String requestUrl = String.format("%s%s", authTokenUrl, authTokenApi);

        HttpResponse response = HttpUtil.createPost(requestUrl)
                .header(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(compositeAuthTokenBody())
                .execute();

        if (response.isOk()) {
            if (log.isInfoEnabled()) {
                log.info("[DefaultAuthTokenService] -> Token Auth Http Request Success!");
                log.info("[TokenAuth] -> response headers: \n{}", response.headers());
            }

            String x_subject_token = response.header("X-Subject-Token");
            if (StringUtils.isBlank(x_subject_token)) {
                throw new XSubjectTokenEmptyException();
            }

            // cache x-subject-token
            repository.save(x_subject_token, properties.getUsername());

            return x_subject_token;
        }
        return "";
    }

    public String compositeAuthTokenBody() {

        JSONObject body = new JSONObject();

        // "domain"
        JSONObject domain = new JSONObject();
        domain.put("name", properties.getDomainName());

        // "user"
        JSONObject user = new JSONObject();
        user.put("name", properties.getUsername());
        user.put("password", properties.getPassword());
        user.put("domain", domain);

        // "password"
        JSONObject password = new JSONObject();
        password.put("user", user);

        // "identity"
        JSONObject identity = new JSONObject();
        identity.put("methods", Collections.singletonList("password"));
        identity.put("password", password);

        // "project"
        JSONObject project = new JSONObject();
        project.put("name", properties.getProjectName());

        // "scope"
        JSONObject scope = new JSONObject();
        scope.put("project", project);

        // "auth"
        JSONObject auth = new JSONObject();
        auth.put("identity", identity);
        auth.put("scope", scope);

        body.put("auth", auth);

        String requestBody = JSONObject.toJSONString(body, true);

        log.info("[TokenAuth] -> request body: \n{}", requestBody);

        return requestBody;
    }

    @Override
    protected void preCheck(HuaweiOcrProperties properties) {
        if (StringUtils.isBlank(properties.getAuthTokenUrl())) {
            throw new InvalidAuthTokenUrlException();
        }
    }
}
