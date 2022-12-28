package io.github.liuruinian.fanco.server.restapi.activities;

import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.fanco.core.entity.ActivityListParam;
import io.github.liuruinian.fanco.core.entity.NoSenseActivity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractActivitiesService implements ActivitiesService {

    @Override
    public JSONObject list(ActivityListParam listParam) {
        return handleList(listParam);
    }

    protected abstract JSONObject handleList(ActivityListParam listParam);

    @Override
    public JSONObject detail(Integer activityId) {
        return handleDetail(activityId);
    }

    protected abstract JSONObject handleDetail(Integer activityId);

    @Override
    public String composeActivityUrl(NoSenseActivity param) {
        // get authorization_code
        String authorizationCode = getAuthorizationCode(param);

        // get partner_access_token
        String partnerAccessToken = getPartnerAccessToken(authorizationCode);

        // get activity url
        String activityUrl = getActivityUrl(partnerAccessToken, param);

        if (log.isInfoEnabled()) {
            log.info("activity url: {}", activityUrl);
        }

        return activityUrl;
    }

    protected abstract String getActivityUrl(String partnerAccessToken, NoSenseActivity param);

    protected abstract String getPartnerAccessToken(String authorizationCode);

    protected abstract String getAuthorizationCode(NoSenseActivity param);

}
