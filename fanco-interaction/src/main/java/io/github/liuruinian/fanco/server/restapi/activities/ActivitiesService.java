package io.github.liuruinian.fanco.server.restapi.activities;

import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.fanco.core.entity.ActivityListParam;

/**
 * activities service
 *
 * @author liuruinian
 * @version 2022-12-26
 */
public interface ActivitiesService {

    /**
     * get activities list
     *
     * @param listParam list param
     * @return activities
     */
    JSONObject list(ActivityListParam listParam);

    /**
     * get activity detail
     *
     * @param activityId activity id
     * @return activity detail
     */
    JSONObject detail(Integer activityId);
}
