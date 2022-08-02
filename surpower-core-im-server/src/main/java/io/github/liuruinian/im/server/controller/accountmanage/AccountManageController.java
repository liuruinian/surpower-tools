package io.github.liuruinian.im.server.controller.accountmanage;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import io.github.liuruinian.im.core.params.accountmanage.*;
import io.github.liuruinian.im.core.restapi.AccountManageApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author liuruinian
 * @version 2022-02-06
 * <p>
 *     账号管理Controller
 * </p>
 */
@Api(tags = "账号管理API")
@RestController
public class AccountManageController {

    private AccountManageApi accountManageApi;

    @Autowired
    public void setAccountManageApi(AccountManageApi accountManageApi) {
        this.accountManageApi = accountManageApi;
    }

    @ApiOperation("导入单个账号")
    public JSONObject accountImport(@RequestBody AccountImportParam accountImportParam) {
        Preconditions.checkNotNull(accountImportParam, "[AccountManageController] -> accountImportParam is null!");
        accountImportParam.checkRequired();

        String userId = accountImportParam.getUserId();
        String nick = accountImportParam.getNick();
        String faceUrl = accountImportParam.getFaceUrl();
        String responseBody = accountManageApi.accountImport(userId, nick, faceUrl);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("导入多个账号")
    public JSONObject multiAccountImport(@RequestBody MultiAccountImportParam multiAccountImportParam) {
        Preconditions.checkNotNull(multiAccountImportParam, "[AccountManageController] -> multiAccountImportParam is null!");
        multiAccountImportParam.checkRequired();

        List<String> accounts = multiAccountImportParam.getAccounts();
        String responseBody = accountManageApi.multiAccountImport(accounts);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("删除账号")
    public JSONObject accountDelete(@RequestBody AccountDeleteParam accountDeleteParam) {
        Preconditions.checkNotNull(accountDeleteParam, "[AccountManageController] -> accountDeleteParam is null!");
        accountDeleteParam.checkRequired();

        Set<String> userIds = accountDeleteParam.getUserIds();
        String responseBody = accountManageApi.accountDelete(userIds);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("检查账号是否导入IM")
    public JSONObject accountCheckImport(@RequestBody AccountCheckParam accountCheckParam) {
        Preconditions.checkNotNull(accountCheckParam, "[AccountManageController] -> accountCheckParam is null!");
        accountCheckParam.checkRequired();

        Set<String> userIds = accountCheckParam.getUserIds();
        String responseBody = accountManageApi.accountCheckImport(userIds);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("失效账号登录状态")
    public JSONObject kick(@RequestBody KickParam kickParam) {
        Preconditions.checkNotNull(kickParam, "[AccountManageController] -> kickParam is null!");
        kickParam.checkRequired();

        String userId = kickParam.getUserId();
        String responseBody = accountManageApi.kick(userId);

        return JSONObject.parseObject(responseBody);
    }

    @ApiOperation("查询账号在线状态")
    public JSONObject queryOnlineStatus(@RequestBody QueryOnlineStatusParam queryOnlineStatusParam) {
        Preconditions.checkNotNull(queryOnlineStatusParam, "[AccountManageController] -> queryOnlineStatusParam is null!");
        queryOnlineStatusParam.checkRequired();

        Set<String> userIds = queryOnlineStatusParam.getUserIds();
        Integer isNeedDetail = queryOnlineStatusParam.getIsNeedDetail();
        if (isNeedDetail == null) {
            isNeedDetail = 0;
        }

        String responseBody = accountManageApi.queryOnlineStatus(userIds, isNeedDetail);

        return JSONObject.parseObject(responseBody);
    }
}
