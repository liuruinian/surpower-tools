package io.github.liuruinian.fanco.server.restapi.activities;

import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.fanco.core.constants.FancoConstants;
import io.github.liuruinian.fanco.core.entity.ActivityListParam;
import io.github.liuruinian.fanco.core.exception.ActivityIdEmptyException;
import io.github.liuruinian.fanco.core.token.AuthTokenService;
import io.github.liuruinian.fanco.server.properties.FancoProperties;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

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
            URIBuilder builder = new URIBuilder(FancoConstants.ACTIVITIES_LIST_API_URL);
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
}
