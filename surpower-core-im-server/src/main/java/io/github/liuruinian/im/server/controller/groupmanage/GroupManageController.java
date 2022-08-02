package io.github.liuruinian.im.server.controller.groupmanage;

import io.github.liuruinian.im.core.restapi.GroupManageApi;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "群组管理API")
public class GroupManageController {

    private GroupManageApi groupManageApi;

    @Autowired
    public void setGroupManageApi(GroupManageApi groupManageApi) {
        this.groupManageApi = groupManageApi;
    }


}
