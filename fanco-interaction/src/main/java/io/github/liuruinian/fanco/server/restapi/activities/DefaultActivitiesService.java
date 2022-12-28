package io.github.liuruinian.fanco.server.restapi.activities;

import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.fanco.core.constants.FancoConstants;
import io.github.liuruinian.fanco.core.entity.ActivityListParam;
import io.github.liuruinian.fanco.core.entity.NoSenseActivity;
import io.github.liuruinian.fanco.core.exception.ActivityIdEmptyException;
import io.github.liuruinian.fanco.core.token.AuthTokenService;
import io.github.liuruinian.fanco.server.properties.FancoProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Slf4j
public class DefaultActivitiesService extends AbstractActivitiesService {

    private final FancoProperties properties;

    private final AuthTokenService authTokenService;

    public DefaultActivitiesService(FancoProperties properties, AuthTokenService authTokenService) {
        this.properties = properties;
        this.authTokenService = authTokenService;
    }

    @Override
    protected JSONObject handleList(ActivityListParam listParam) {
        String accessToken = authTokenService.obtainRefreshToken();
        JSONObject jt = JSONObject.parseObject(accessToken);
        String token = jt.getString("access_token");

        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(FancoConstants.ACTIVITIES_LIST_API_URL);

            if (properties.getAid() != null) {
                builder.setParameter("aid", String.valueOf(properties.getAid()));
            }

            String s = JSONObject.toJSONString(listParam);
            JSONObject param = JSONObject.parseObject(s);
            Set<Map.Entry<String, Object>> entries = param.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                String key = entry.getKey();
                Object value = entry.getValue();
                builder.setParameter(key, String.valueOf(value));
            }

            HttpGet httpGet = new HttpGet(builder.build());
            httpGet.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            httpGet.setHeader("Authorization", "Bearer " + token);

            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                return JSONObject.parseObject(content);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected JSONObject handleDetail(Integer activityId) {
        if (activityId == null) {
            throw new ActivityIdEmptyException();
        }

        String accessToken = authTokenService.obtainRefreshToken();
        JSONObject jt = JSONObject.parseObject(accessToken);
        String token = jt.getString("access_token");

        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        try {
            URIBuilder builder = new URIBuilder(FancoConstants.ACTIVITIES_DETAIL_API_URL);
            builder.setParameter("activityId", String.valueOf(activityId));

            if (properties.getAid() != null) {
                builder.setParameter("aid", String.valueOf(properties.getAid()));
            }

            HttpGet httpGet = new HttpGet(builder.build());
            httpGet.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            httpGet.setHeader("Authorization", "Bearer " + token);

            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                return JSONObject.parseObject(content);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected String getActivityUrl(String partnerAccessToken, NoSenseActivity param) {
        String activityUrl = param.getActivityUrl() + "&partner_access_token=" + partnerAccessToken;
        if (log.isInfoEnabled()) {
            log.info("activity url: {}", activityUrl);
        }

        return activityUrl;
    }

    @Override
    protected String getPartnerAccessToken(String authorizationCode) {
        String authTokenUrl = String.format(FancoConstants.OAUTH_TOKEN_URL + "?grant_type=authorization_code&redirect_uri=%s&code=%s",
                properties.getAuthorizationCodeUrl(),
                authorizationCode);
        HttpResponse ret = HttpUtil.createPost(authTokenUrl)
                .header(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(Header.AUTHORIZATION, "Basic " + encryptClientIdSecret())
                .execute();
        JSONObject pat = JSONObject.parseObject(ret.body());
        String partnerAccessToken = pat.getString("access_token");
        if (log.isInfoEnabled()) {
            log.info("partner_access_token: {}", partnerAccessToken);
        }
        return partnerAccessToken;
    }

    private String encryptClientIdSecret() {
        String clientId = properties.getClientId();
        String clientSecret = properties.getClientSecret();
        return Base64Utils.encodeToString((clientId + ":" + clientSecret).getBytes());
    }

    @Override
    protected String getAuthorizationCode(NoSenseActivity param) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(FancoConstants.OAUTH_AUTHORIZE_URL);
            builder.setParameter("client_id", properties.getClientId());
            builder.setParameter("redirect_uri", properties.getAuthorizationCodeUrl());
            builder.setParameter("response_type", "code");
            builder.setParameter("scope", "all");
            builder.setParameter("auth_uid", param.getAuthUid());


            HttpGet httpGet = new HttpGet(builder.build());
            httpGet.setHeader("Content-Type", "text/plain;charset=UTF-8");

            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                if (log.isInfoEnabled()) {
                    log.info("authorization_code: {}", content);
                }
                return content;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }
}
