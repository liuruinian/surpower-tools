package io.github.liuruinian.im.server.autoconfig;

import com.alibaba.fastjson.JSONObject;
import io.github.liuruinian.im.core.params.accountmanage.*;
import io.github.liuruinian.im.core.params.globalmute.GetNoSpeakingParam;
import io.github.liuruinian.im.core.params.globalmute.SetNoSpeakingParam;
import io.github.liuruinian.im.core.params.operationmanage.GetAppInfoParam;
import io.github.liuruinian.im.core.params.operationmanage.GetHistoryMsgParam;
import io.github.liuruinian.im.core.params.portrait.PortraitGetParam;
import io.github.liuruinian.im.core.params.portrait.PortraitSetParam;
import io.github.liuruinian.im.core.params.recentcontacts.DeleteSessionParam;
import io.github.liuruinian.im.core.params.recentcontacts.GetListParam;
import io.github.liuruinian.im.core.params.relationchain.*;
import io.github.liuruinian.im.core.params.singlechat.*;
import io.github.liuruinian.im.core.restapi.*;
import io.github.liuruinian.im.core.sign.UserSignService;
import io.github.liuruinian.im.server.api.accountmanage.DefaultAccountManageService;
import io.github.liuruinian.im.server.api.allpush.DefaultAllMemberPushApi;
import io.github.liuruinian.im.server.api.globalmute.DefaultGlobalMuteManageApi;
import io.github.liuruinian.im.server.api.groupmanage.DefaultGroupManageApi;
import io.github.liuruinian.im.server.api.operationmanage.DefaultOperationManageApi;
import io.github.liuruinian.im.server.api.portrait.DefaultPortraitManageApi;
import io.github.liuruinian.im.server.api.recentcontacts.DefaultRecentContactsApi;
import io.github.liuruinian.im.server.api.relationchain.DefaultRelationChainApi;
import io.github.liuruinian.im.server.api.singlechat.DefaultSingleChatApi;
import io.github.liuruinian.im.server.controller.accountmanage.AccountManageController;
import io.github.liuruinian.im.server.controller.allpush.AllMemberPushController;
import io.github.liuruinian.im.server.controller.callback.CallbackController;
import io.github.liuruinian.im.server.controller.globalmute.GlobalMuteController;
import io.github.liuruinian.im.server.controller.groupmanage.GroupManageController;
import io.github.liuruinian.im.server.controller.operationmanage.OperationManageController;
import io.github.liuruinian.im.server.controller.portrait.PortraitController;
import io.github.liuruinian.im.server.controller.recentcontacts.RecentContactsController;
import io.github.liuruinian.im.server.controller.relationchain.RelationChainController;
import io.github.liuruinian.im.server.controller.sign.UserSignController;
import io.github.liuruinian.im.server.controller.singlechat.SingleChatController;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import io.github.liuruinian.im.server.properties.RedisUserSignProperties;
import io.github.liuruinian.im.server.properties.UserSignProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author liuruinian
 * @version 2022-02-06
 * <p>
 *     IM服务端自动装配类
 * </p>
 */
@Configuration
@EnableConfigurationProperties(value = {ImServerProperties.class, UserSignProperties.class, RedisUserSignProperties.class})
@ComponentScan(basePackages = {
        "io.github.liuruinian.im.server.repository",
        "io.github.liuruinian.im.server.sign",
        "io.github.liuruinian.im.server.callback",
        "io.github.liuruinian.im.server.controller"
})
@ConditionalOnWebApplication
@Slf4j
public class ImServerAutoConfiguration {

    private final static String BASE_PATH = "/tim";

    /**
     * 创建账号管理服务Bean
     */
    @Bean
    @Lazy
    public AccountManageApi accountManageApi(ImServerProperties imServerProperties,
                                             UserSignService userSignService,
                                             ApplicationEventPublisher eventPublisher
                                             ) {
        return new DefaultAccountManageService(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * 创建单聊服务Bean
     */
    @Bean
    @Lazy
    public SingleChatApi singleChatApi(ImServerProperties imServerProperties,
                                       UserSignService userSignService,
                                       ApplicationEventPublisher eventPublisher) {
        return new DefaultSingleChatApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * 创建全员推送Bean
     */
    @Bean
    @Lazy
    public AllMemberPushApi allMemberPushApi(ImServerProperties imServerProperties,
                                             UserSignService userSignService,
                                             ApplicationEventPublisher eventPublisher) {
        return new DefaultAllMemberPushApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * 创建资料设置Bean
     */
    @Bean
    @Lazy
    public PortraitManageApi portraitManageApi(ImServerProperties imServerProperties,
                                               UserSignService userSignService,
                                               ApplicationEventPublisher eventPublisher) {
        return new DefaultPortraitManageApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * 创建关系链Bean
     */
    @Bean
    @Lazy
    public RelationChainManageApi relationChainManageApi(ImServerProperties imServerProperties,
                                                         UserSignService userSignService,
                                                         ApplicationEventPublisher eventPublisher) {
        return new DefaultRelationChainApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * 创建最近联系人Bean
     */
    @Bean
    @Lazy
    public RecentContactsApi recentContactsApi(ImServerProperties imServerProperties,
                                               UserSignService userSignService,
                                               ApplicationEventPublisher eventPublisher) {
        return new DefaultRecentContactsApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * 创建群组管理Bean
     */
    @Bean
    @Lazy
    public GroupManageApi groupManageApi(ImServerProperties imServerProperties,
                                         UserSignService userSignService,
                                         ApplicationEventPublisher eventPublisher) {
        return new DefaultGroupManageApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * 创建全员禁言管理
     */
    @Bean
    @Lazy
    public GlobalMuteManageApi globalMuteManageApi(ImServerProperties imServerProperties,
                                                   UserSignService userSignService,
                                                   ApplicationEventPublisher eventPublisher) {
        return new DefaultGlobalMuteManageApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * 创建运营管理Bean
     */
    @Bean
    @Lazy
    public OperationManageApi operationManageApi(ImServerProperties imServerProperties,
                                                 UserSignService userSignService,
                                                 ApplicationEventPublisher eventPublisher) {
        return new DefaultOperationManageApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * 设置UserSign控制器映射
     *
     * @param mapping
     *         RequestMappingHandlerMapping
     * @param controller
     *         UserSignController
     * @throws NoSuchMethodException
     *         if a matching method is not found
     *         or if the name is "&lt;init&gt;"or "&lt;clinit&gt;".
     * @throws SecurityException
     *         If a security manager, <i>s</i>, is present and
     *         the caller's class loader is not the same as or an
     *         ancestor of the class loader for the current class and
     *         invocation of {@link SecurityManager#checkPackageAccess
     *         s.checkPackageAccess()} denies access to the package
     *         of this class.
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setUserSignWebMapping(RequestMappingHandlerMapping mapping,
                                      UserSignController controller) throws NoSuchMethodException, SecurityException {

        Method userSignMethod = UserSignController.class.getMethod("generateUserSign", String.class);
        RequestMappingInfo mappingInfo = RequestMappingInfo.paths(BASE_PATH + "/user_sign/{user_id}")
                .methods(RequestMethod.GET).build();

        mapping.registerMapping(mappingInfo, controller, userSignMethod);
    }

    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setCallbackWebMapping(RequestMappingHandlerMapping mapping,
                                      CallbackController controller) throws NoSuchMethodException, SecurityException {

        Method callbackMethod = CallbackController.class.getMethod("imcallback", JSONObject.class);
        RequestMappingInfo mappingInfo = RequestMappingInfo.paths(BASE_PATH + "/imcallback")
                .methods(RequestMethod.POST).build();

        mapping.registerMapping(mappingInfo, controller, callbackMethod);
    }

    /**
     * 设置账号管理控制器映射
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setAccountManageWebMapping(RequestMappingHandlerMapping mapping,
                                      AccountManageController controller) throws NoSuchMethodException, SecurityException {

        // 导入单个账号
        Method accountImportMethod = AccountManageController.class.getMethod("accountImport", AccountImportParam.class);
        RequestMappingInfo accountImportMapping = RequestMappingInfo
                .paths(BASE_PATH + "/im_open_login_svc/account_import")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(accountImportMapping, controller, accountImportMethod);

        // 导入多个账号
        Method multiAccountImportMethod = AccountManageController.class.getMethod("multiAccountImport", MultiAccountImportParam.class);
        RequestMappingInfo multiAccountImportMapping = RequestMappingInfo
                .paths(BASE_PATH + "/im_open_login_svc/multiaccount_import")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(multiAccountImportMapping, controller, multiAccountImportMethod);

        // 删除账号
        Method accountDeleteMethod = AccountManageController.class.getMethod("accountDelete", AccountDeleteParam.class);
        RequestMappingInfo accountDeleteMapping = RequestMappingInfo
                .paths(BASE_PATH + "/im_open_login_svc/account_delete")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(accountDeleteMapping, controller, accountDeleteMethod);

        // 检查账号是否导入IM
        Method accountCheckImportMethod = AccountManageController.class.getMethod("accountCheckImport", AccountCheckParam.class);
        RequestMappingInfo accountCheckImportMapping = RequestMappingInfo
                .paths(BASE_PATH + "/im_open_login_svc/account_check")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(accountCheckImportMapping, controller, accountCheckImportMethod);

        // 失效账号登录状态
        Method kickMethod = AccountManageController.class.getMethod("kick", KickParam.class);
        RequestMappingInfo kickMapping = RequestMappingInfo
                .paths(BASE_PATH + "/im_open_login_svc/kick")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(kickMapping, controller, kickMethod);

        // 查询账号在线状态
        Method queryOnlineStatusMethod = AccountManageController.class.getMethod("queryOnlineStatus", QueryOnlineStatusParam.class);
        RequestMappingInfo queryOnlineStatusMapping = RequestMappingInfo
                .paths(BASE_PATH + "/openim/query_online_status")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(queryOnlineStatusMapping, controller, queryOnlineStatusMethod);
    }

    /**
     * 设置单聊消息控制器映射
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setAccountManageWebMapping(RequestMappingHandlerMapping mapping,
                                           SingleChatController controller) throws NoSuchMethodException, SecurityException {

        // 单聊单发消息
        Method sendMsgMethod = SingleChatController.class.getMethod("sendMsg", SingleIssueSingleChatParam.class);
        RequestMappingInfo sendMsgMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openim/sendmsg")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(sendMsgMappingInfo, controller, sendMsgMethod);

        // 批量发单聊消息
        Method batchSendMsgMethod = SingleChatController.class.getMethod("batchSendMsg", BatchSingleChatParam.class);
        RequestMappingInfo batchSendMsgMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openim/batchsendmsg")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(batchSendMsgMappingInfo, controller, batchSendMsgMethod);

        // 导入单聊消息
        Method importMsgMethod = SingleChatController.class.getMethod("importMsg", ImportSingleChatParam.class);
        RequestMappingInfo importMsgMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openim/importmsg")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(importMsgMappingInfo, controller, importMsgMethod);

        // 查询单聊消息
        Method adminGetroamMsgMethod = SingleChatController.class.getMethod("admin_getroammsg", QuerySingleChatParam.class);
        RequestMappingInfo adminGetroamMsgMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openim/admin_getroammsg")
                .methods(RequestMethod.POST).build();

        mapping.registerMapping(adminGetroamMsgMappingInfo, controller, adminGetroamMsgMethod);

        // 撤回单聊消息
        Method adminMsgWithdrawMethod = SingleChatController.class.getMethod("admin_msgwithdraw", WithdrawSingleChatParam.class);
        RequestMappingInfo adminMsgWithdrawMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openim/admin_msgwithdraw")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(adminMsgWithdrawMappingInfo, controller, adminMsgWithdrawMethod);

        // 设置单聊消息已读
        Method adminSetMsgReadMethod = SingleChatController.class.getMethod("admin_set_msg_read", ReadSingleChatParam.class);
        RequestMappingInfo adminSetMsgReadMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openim/admin_set_msg_read")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(adminSetMsgReadMappingInfo, controller, adminSetMsgReadMethod);

        // 查询单聊未读消息计数
        Method getC2cUnreadMsgNumMethod = SingleChatController.class.getMethod("get_c2c_unread_msg_num", C2CUnreadMsgNumParam.class);
        RequestMappingInfo getC2cUnreadMsgNumMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openim/get_c2c_unread_msg_num")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(getC2cUnreadMsgNumMappingInfo, controller, getC2cUnreadMsgNumMethod);
    }

    /**
     * 设置全员推送web映射
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setAllPushWebMapping(RequestMappingHandlerMapping mapping,
                                     AllMemberPushController controller) throws NoSuchMethodException, SecurityException {

    }

    /**
     * 设置资料管理web映射
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setPortraitManageWebMapping(RequestMappingHandlerMapping mapping,
                                      PortraitController controller) throws NoSuchMethodException, SecurityException {

        // 资料设置
        Method portraitSetMethod = PortraitController.class.getMethod("portrait_set", PortraitSetParam.class);
        RequestMappingInfo portraitSetMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/profile/portrait_set")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(portraitSetMappingInfo, controller, portraitSetMethod);

        // 拉取资料
        Method portraitGetMethod = PortraitController.class.getMethod("portrait_get", PortraitGetParam.class);
        RequestMappingInfo portraitGetMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/profile/portrait_get")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(portraitGetMappingInfo, controller, portraitGetMethod);
    }

    /**
     * 设置关系链web映射
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setRelationChainWebMapping(RequestMappingHandlerMapping mapping,
                                     RelationChainController controller) throws NoSuchMethodException, SecurityException {

        // 添加好友
        Method friendAddMethod = RelationChainController.class.getMethod("friendAdd", FriendAddParam.class);
        RequestMappingInfo friendAddMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_add")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendAddMappingInfo, controller, friendAddMethod);

        // 导入好友
        Method friendImportMethod = RelationChainController.class.getMethod("friendImport", FriendImportParam.class);
        RequestMappingInfo friendImportMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_import")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendImportMappingInfo, controller, friendImportMethod);

        // 更新好友
        Method friendUpdateMethod = RelationChainController.class.getMethod("friendUpdate", FriendUpdateParam.class);
        RequestMappingInfo friendUpdateMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_update")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendUpdateMappingInfo, controller, friendUpdateMethod);

        // 删除好友
        Method friendDeleteMethod = RelationChainController.class.getMethod("friendDelete", FriendDeleteParam.class);
        RequestMappingInfo friendDeleteMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_delete")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendDeleteMappingInfo, controller, friendDeleteMethod);

        // 删除所有好友
        Method friendDeleteAllMethod = RelationChainController.class.getMethod("friendDeleteAll", FriendDeleteAllParam.class);
        RequestMappingInfo friendDeleteAllMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_delete_all")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendDeleteAllMappingInfo, controller, friendDeleteAllMethod);

        // 检验好友
        Method friendCheckMethod = RelationChainController.class.getMethod("friendCheck", FriendCheckParam.class);
        RequestMappingInfo friendCheckMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_check")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendCheckMappingInfo, controller, friendCheckMethod);

        // 拉取好友
        Method friendGetMethod = RelationChainController.class.getMethod("friendGet", FriendGetParam.class);
        RequestMappingInfo friendGetMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_get")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendGetMappingInfo, controller, friendGetMethod);

        // 拉取指定好友资料和数据
        Method friendGetListMethod = RelationChainController.class.getMethod("friendGetList", FriendGetListParam.class);
        RequestMappingInfo friendGetListMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_get_list")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendGetListMappingInfo, controller, friendGetListMethod);

        // 添加黑名单
        Method blackListAddMethod = RelationChainController.class.getMethod("blackListAdd", BlackListAddParam.class);
        RequestMappingInfo blackListAddMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/black_list_add")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(blackListAddMappingInfo, controller, blackListAddMethod);

        // 删除黑名单
        Method blackListDeleteMethod = RelationChainController.class.getMethod("blackListDelete", BlackListDeleteParam.class);
        RequestMappingInfo blackListDeleteMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/black_list_delete")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(blackListDeleteMappingInfo, controller, blackListDeleteMethod);

        // 拉取黑名单
        Method blackListGetMethod = RelationChainController.class.getMethod("blackListGet", BlackListGetParam.class);
        RequestMappingInfo blackListGetMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/black_list_get")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(blackListGetMappingInfo, controller, blackListGetMethod);

        // 校验黑名单
        Method blackListCheckMethod = RelationChainController.class.getMethod("blackListCheck", BlackListCheckParam.class);
        RequestMappingInfo blackListCheckMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/black_list_check")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(blackListCheckMappingInfo, controller, blackListCheckMethod);

        // 添加分组
        Method groupAddMethod = RelationChainController.class.getMethod("groupAdd", GroupAddParam.class);
        RequestMappingInfo groupAddMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/group_add")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(groupAddMappingInfo, controller, groupAddMethod);

        // 删除分组
        Method groupDeleteMethod = RelationChainController.class.getMethod("groupDelete", GroupDeleteParam.class);
        RequestMappingInfo groupDeleteMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/group_delete")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(groupDeleteMappingInfo, controller, groupDeleteMethod);

        // 拉取分组
        Method groupGetMethod = RelationChainController.class.getMethod("groupGet", GroupGetParam.class);
        RequestMappingInfo groupGetMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/group_get")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(groupGetMappingInfo, controller, groupGetMethod);
    }

    /**
     * 设置最近联系人控制器映射
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setRecentContactsWebMapping(RequestMappingHandlerMapping mapping,
                                           RecentContactsController controller) throws NoSuchMethodException, SecurityException {

        // 拉取会话列表
        Method getListMethod = RecentContactsController.class.getMethod("getList", GetListParam.class);
        RequestMappingInfo getListMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/recentcontact/get_list")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(getListMappingInfo, controller, getListMethod);

        // 删除会话
        Method deleteSessionMethod = RecentContactsController.class.getMethod("deleteSession", DeleteSessionParam.class);
        RequestMappingInfo deleteSessionMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/recentcontact/delete")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(deleteSessionMappingInfo, controller, deleteSessionMethod);
    }

    /**
     * 设置群组管理控制器映射
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setGroupManageWebMapping(RequestMappingHandlerMapping mapping,
                                      GroupManageController controller) throws NoSuchMethodException, SecurityException {

    }

    /**
     * 设置全局禁言管理控制器映射
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setGlobalMuteWebMapping(RequestMappingHandlerMapping mapping,
                                      GlobalMuteController controller) throws NoSuchMethodException, SecurityException {

        // 设置全员禁言
        Method setnospeaking = GlobalMuteController.class.getMethod("setnospeaking", SetNoSpeakingParam.class);
        RequestMappingInfo setNoSpeakingMappingInfo = RequestMappingInfo.paths(BASE_PATH + "/openconfigsvr/setnospeaking")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(setNoSpeakingMappingInfo, controller, setnospeaking);

        // 查询全员禁言
        Method getnospeaking = GlobalMuteController.class.getMethod("getnospeaking", GetNoSpeakingParam.class);
        RequestMappingInfo getNoSpeakingMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openconfigsvr/getnospeaking")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(getNoSpeakingMappingInfo, controller, getnospeaking);
    }

    /**
     * 设置运营管理控制器映射
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setOperationManageWebMapping(RequestMappingHandlerMapping mapping,
                                             OperationManageController controller) throws NoSuchMethodException, SecurityException {

        // 拉取运营数据
        Method getAppInfo = OperationManageController.class.getMethod("getAppInfo", GetAppInfoParam.class);
        RequestMappingInfo getAppInfoMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openconfigsvr/getappinfo")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(getAppInfoMappingInfo, controller, getAppInfo);

        // 下载最新消息记录
        Method getHistoryMsg = OperationManageController.class.getMethod("getHistoryMsg", GetHistoryMsgParam.class);
        RequestMappingInfo getHistoryMsgMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/open_msg_svc/get_history")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(getHistoryMsgMappingInfo, controller, getHistoryMsg);

        // 获取服务器IP地址
        Method getIpList = OperationManageController.class.getMethod("getIpList");
        RequestMappingInfo getIpListMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/ConfigSvc/GetIPList")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(getIpListMappingInfo, controller, getIpList);
    }
}
