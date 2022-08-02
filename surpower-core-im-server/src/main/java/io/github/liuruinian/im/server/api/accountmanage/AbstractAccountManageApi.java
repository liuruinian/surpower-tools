package io.github.liuruinian.im.server.api.accountmanage;

import io.github.liuruinian.im.core.restapi.AccountManageApi;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.api.AbstractImServerApi;
import io.github.liuruinian.im.server.events.accountmanage.*;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Set;

/**
 * @author liuruinian
 * @version 2022-02-06
 * <p>
 *      抽象AbstractAccountManageApi
 * </p>
 */
@Slf4j
public abstract class AbstractAccountManageApi extends AbstractImServerApi implements AccountManageApi {

    private final ApplicationEventPublisher eventPublisher;

    public AbstractAccountManageApi(ImServerProperties imServerProperties,
                                    UserSignService userSignService,
                                    ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService);
        this.eventPublisher = eventPublisher;
    }

    @Override
    public String accountImport(String userId, String nick, String faceUrl) {
        String responseBody = null;
        try {
            responseBody = handleAccountImport(userId, nick, faceUrl);
            publishAccountImportSuccessEvent(userId, nick, faceUrl, responseBody);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractAccountManageApi] -> account import occurs error!", e);
            }
            publishAccountImportFailEvent(userId, nick, faceUrl, responseBody, e);
        }

        return responseBody;
    }

    protected abstract String handleAccountImport(String userId, String nick, String faceUrl);

    /**
     * 发布导入单个账号成功事件
     *
     * @param userId        用户名
     * @param nick          用户昵称
     * @param faceUrl       用户头像URL
     * @param responseBody  响应结果
     */
    protected final void publishAccountImportSuccessEvent(String userId, String nick, String faceUrl, String responseBody) {
        if (eventPublisher == null) {
            return;
        }
        if (log.isInfoEnabled()) {
            log.info("[AbstractAccountManageApi] -> publish account import success event!");
        }
        eventPublisher.publishEvent(new AccountImportSuccessEvent(this, userId, nick, faceUrl, responseBody));
    }

    /**
     * 发布导入单个账号失败事件
     *
     * @param userId        用户名
     * @param nick          用户昵称
     * @param faceUrl       用户头像URL
     * @param responseBody  响应结果
     * @param throwable     异常信息
     */
    protected final void publishAccountImportFailEvent(String userId, String nick, String faceUrl, String responseBody, Throwable throwable) {
        if (eventPublisher == null) {
            return;
        }
        if (log.isInfoEnabled()) {
            log.info("[AbstractAccountManageApi] -> publish account import fail event!");
        }
        eventPublisher.publishEvent(new AccountImportFailEvent(this, userId, nick, faceUrl, responseBody, throwable));
    }

    @Override
    public String multiAccountImport(List<String> accounts) {
        String responseBody = null;
        try {
            responseBody = handleMultiAccountImport(accounts);
            publishMultiAccountImportSuccessEvent(accounts, responseBody);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractAccountManageApi] -> multi account import occurs error!", e);
            }
            publishMultiAccountImportFailEvent(accounts, responseBody, e);
        }
        return responseBody;
    }

    protected abstract String handleMultiAccountImport(List<String> accounts);

    /**
     * 发布导入多个账号成功事件
     *
     * @param accounts      用户名集合
     * @param responseBody  响应结果
     */
    protected final void publishMultiAccountImportSuccessEvent(List<String> accounts, String responseBody) {
        if (eventPublisher == null) {
            return;
        }
        if (log.isInfoEnabled()) {
            log.info("[AbstractAccountManageApi] -> publish multi account import success event!");
        }
        eventPublisher.publishEvent(new MultiAccountImportSuccessEvent(this, accounts, responseBody));
    }

    /**
     * 发布导入多个账号失败事件
     *
     * @param accounts      用户名集合
     * @param responseBody  响应结果
     * @param throwable     异常信息
     */
    protected final void publishMultiAccountImportFailEvent(List<String> accounts, String responseBody, Throwable throwable) {
        if (eventPublisher == null) {
            return;
        }
        if (log.isInfoEnabled()) {
            log.info("[AbstractAccountManageApi] -> publish multi account import fail event!");
        }
        eventPublisher.publishEvent(new MultiAccountImportFailEvent(this, accounts, responseBody, throwable));
    }

    @Override
    public String accountDelete(Set<String> userIds) {
        String responseBody = null;

        try {
            responseBody = handleAccountDelete(userIds);
            publishAccountDeleteSuccessEvent(userIds, responseBody);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractAccountManageApi] -> account delete occurs error!", e);
            }
            publishAccountDeleteFailEvent(userIds, responseBody, e);
        }
        return responseBody;
    }

    protected abstract String handleAccountDelete(Set<String> userIds);

    /**
     * 发布账号删除成功事件
     *
     * @param userIds       用户名集合
     * @param responseBody  响应结果
     */
    protected final void publishAccountDeleteSuccessEvent(Set<String> userIds, String responseBody) {
        if (eventPublisher == null) {
            return;
        }
        if (log.isInfoEnabled()) {
            log.info("[AbstractAccountManageApi] -> publish account delete success event!");
        }
        eventPublisher.publishEvent(new AccountDeleteSuccessEvent(this, userIds, responseBody));
    }

    /**
     * 发布账号删除失败事件
     *
     * @param userIds       用户名集合
     * @param responseBody  响应结果
     * @param throwable     异常信息
     */
    protected final void publishAccountDeleteFailEvent(Set<String> userIds, String responseBody, Throwable throwable) {
        if (eventPublisher == null) {
            return;
        }
        if (log.isInfoEnabled()) {
            log.info("[AbstractAccountManageApi] -> publish account delete fail event!");
        }
        eventPublisher.publishEvent(new AccountDeleteFailEvent(this, userIds, responseBody, throwable));
    }

    @Override
    public String accountCheckImport(Set<String> userIds) {
        String responseBody = null;
        try {
            responseBody = handleAccountCheckImport(userIds);
            publishAccountCheckSuccessEvent(userIds, responseBody);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractAccountManageApi] -> account check import occurs error!", e);
            }
            publishAccountCheckFailEvent(userIds, responseBody, e);
        }
        return responseBody;
    }

    protected abstract String handleAccountCheckImport(Set<String> userIds);

    /**
     * 发布检查账号导入成功事件
     *
     * @param userIds       用户名集合
     * @param responseBody  响应结果
     */
    protected final void publishAccountCheckSuccessEvent(Set<String> userIds, String responseBody) {
        if (eventPublisher == null) {
            return;
        }
        if (log.isInfoEnabled()) {
            log.info("[AbstractAccountManageApi] -> publish account check success event!");
        }
        eventPublisher.publishEvent(new AccountCheckSuccessEvent(this, userIds, responseBody));
    }

    /**
     * 发布检查账号导入失败事件
     *
     * @param userIds       用户名集合
     * @param responseBody  响应结果
     * @param throwable     异常信息
     */
    protected final void publishAccountCheckFailEvent(Set<String> userIds, String responseBody, Throwable throwable) {
        if (eventPublisher == null) {
            return;
        }
        if (log.isInfoEnabled()) {
            log.info("[AbstractAccountManageApi] -> publish account check fail event!");
        }
        eventPublisher.publishEvent(new AccountCheckFailEvent(this, userIds, responseBody, throwable));
    }

    @Override
    public String kick(String userId) {
        String responseBody = null;
        try {
            responseBody = handleKick(userId);
            // TODO 添加事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractAccountManageApi] -> account kick occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleKick(String userId);

    @Override
    public String queryOnlineStatus(Set<String> userIds, Integer isNeedDetail) {
        String responseBody = null;

        try {
            responseBody = handleQueryOnlineStatus(userIds, isNeedDetail);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractAccountManageApi] -> query online status occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleQueryOnlineStatus(Set<String> userIds, Integer isNeedDetail);
}
