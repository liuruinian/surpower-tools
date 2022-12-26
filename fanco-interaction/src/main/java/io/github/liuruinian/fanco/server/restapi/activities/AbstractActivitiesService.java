package io.github.liuruinian.fanco.server.restapi.activities;

import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.fanco.core.entity.ActivityListParam;

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
}
