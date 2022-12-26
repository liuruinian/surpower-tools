package io.github.liuruinian.fanco.server.controller.activities;

import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.fanco.core.entity.ActivityListParam;
import io.github.liuruinian.fanco.server.restapi.activities.ActivitiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "获取活动API")
public class ActivitiesController {

    private ActivitiesService activitiesService;

    @Autowired
    public void setActivitiesService(ActivitiesService activitiesService) {
        this.activitiesService = activitiesService;
    }

    @ApiOperation("获取活动列表")
    public JSONObject list(@RequestBody ActivityListParam param) {
        return activitiesService.list(param);
    }

    @ApiOperation("获取活动详情")
    @ApiImplicitParam(name = "activity_id", value = "活动ID", dataType = "integer", paramType = "path")
    public JSONObject detail(@PathVariable("activity_id") Integer activityId) {
        return activitiesService.detail(activityId);
    }
}
