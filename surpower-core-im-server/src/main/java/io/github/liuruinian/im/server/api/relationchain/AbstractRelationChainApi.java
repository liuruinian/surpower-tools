package io.github.liuruinian.im.server.api.relationchain;

import io.github.liuruinian.im.core.params.relationchain.*;
import io.github.liuruinian.im.core.restapi.RelationChainManageApi;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.api.AbstractImServerApi;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author liuruinian
 * @version 2022-02-10
 * <p>
 *     关系链API
 * </p>
 */
@Slf4j
public abstract class AbstractRelationChainApi extends AbstractImServerApi implements RelationChainManageApi {

    private final ApplicationEventPublisher eventPublisher;

    public AbstractRelationChainApi(ImServerProperties imServerProperties,
                                    UserSignService userSignService,
                                    ApplicationEventPublisher eventPublisher) {
        super(imServerProperties, userSignService);
        this.eventPublisher = eventPublisher;
    }

    @Override
    public String friend_add(FriendAddParam friendAddParam) {
        String responseBody = null;

        try {
            responseBody = handleFriendAdd(friendAddParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRelationChainApi] -> friend_add occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleFriendAdd(FriendAddParam friendAddParam);

    @Override
    public String friend_import(FriendImportParam friendImportParam) {
        String responseBody = null;

        try {
            responseBody = handleFriendImport(friendImportParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRelationChainApi] -> friend_import occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleFriendImport(FriendImportParam friendImportParam);

    @Override
    public String friend_update(FriendUpdateParam friendUpdateParam) {
        String responseBody = null;

        try {
            responseBody = handleFriendUpdate(friendUpdateParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRelationChainApi] -> friend_update occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleFriendUpdate(FriendUpdateParam friendUpdateParam);

    @Override
    public String friend_delete(FriendDeleteParam friendDeleteParam) {
        String responseBody = null;

        try {
            responseBody = handleFriendDelete(friendDeleteParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRelationChainApi] -> friend_delete occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleFriendDelete(FriendDeleteParam friendDeleteParam);

    @Override
    public String friend_delete_all(FriendDeleteAllParam friendDeleteAllParam) {
        String responseBody = null;

        try {
            responseBody = handleFriendDeleteAll(friendDeleteAllParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRelationChainApi] -> friend_delete_all occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleFriendDeleteAll(FriendDeleteAllParam friendDeleteAllParam);

    @Override
    public String friend_check(FriendCheckParam friendCheckParam) {
        String responseBody = null;

        try {
            responseBody = handleFriendCheck(friendCheckParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRelationChainApi] -> friend_check occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleFriendCheck(FriendCheckParam friendCheckParam);

    @Override
    public String friend_get(FriendGetParam friendGetParam) {
        String responseBody = null;

        try {
            responseBody = handleFriendGet(friendGetParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRelationChainApi] -> friend_get occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleFriendGet(FriendGetParam friendGetParam);

    @Override
    public String friend_get_list(FriendGetListParam friendGetListParam) {
        String responseBody = null;

        try {
            responseBody = handleFriendGetList(friendGetListParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRelationChainApi] -> friend_get_list occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleFriendGetList(FriendGetListParam friendGetListParam);

    @Override
    public String black_list_add(BlackListAddParam blackListAddParam) {
        String responseBody = null;

        try {
            responseBody = handleBlackListAdd(blackListAddParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRelationChainApi] -> black_list_add occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleBlackListAdd(BlackListAddParam blackListAddParam);

    @Override
    public String black_list_delete(BlackListDeleteParam blackListDeleteParam) {
        String responseBody = null;

        try {
            responseBody = handleBlackListDelete(blackListDeleteParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRelationChainApi] -> black_list_delete occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleBlackListDelete(BlackListDeleteParam blackListDeleteParam);

    @Override
    public String black_list_get(BlackListGetParam blackListGetParam) {
        String responseBody = null;

        try {
            responseBody = handleBlackListGet(blackListGetParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRelationChainApi] -> black_list_get occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleBlackListGet(BlackListGetParam blackListGetParam);

    @Override
    public String black_list_check(BlackListCheckParam blackListCheckParam) {
        String responseBody = null;

        try {
            responseBody = handleBlackListCheck(blackListCheckParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRelationChainApi] -> black_list_check occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleBlackListCheck(BlackListCheckParam blackListCheckParam);

    @Override
    public String group_add(GroupAddParam groupAddParam) {
        String responseBody = null;

        try {
            responseBody = handleGroupAdd(groupAddParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRelationChainApi] -> group_add occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleGroupAdd(GroupAddParam groupAddParam);

    @Override
    public String group_delete(GroupDeleteParam groupDeleteParam) {
        String responseBody = null;

        try {
            responseBody = handleGroupDelete(groupDeleteParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRelationChainApi] -> group_delete occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleGroupDelete(GroupDeleteParam groupDeleteParam);

    @Override
    public String group_get(GroupGetParam groupGetParam) {
        String responseBody = null;

        try {
            responseBody = handleGroupGet(groupGetParam);
            // TODO 发布事件
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("[AbstractRelationChainApi] -> group_get occurs error!", e);
            }
        }

        return responseBody;
    }

    protected abstract String handleGroupGet(GroupGetParam groupGetParam);


}
