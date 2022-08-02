package io.github.liuruinian.im.server.controller.allpush;

import io.github.liuruinian.im.core.restapi.AllMemberPushApi;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "全员推送API")
public class AllMemberPushController {

    private AllMemberPushApi allMemberPushApi;

    @Autowired
    public void setAllMemberPushApi(AllMemberPushApi allMemberPushApi) {
        this.allMemberPushApi = allMemberPushApi;
    }
}
