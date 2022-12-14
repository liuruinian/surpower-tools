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
 *     IM????????????????????????
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
     * ????????????????????????Bean
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
     * ??????????????????Bean
     */
    @Bean
    @Lazy
    public SingleChatApi singleChatApi(ImServerProperties imServerProperties,
                                       UserSignService userSignService,
                                       ApplicationEventPublisher eventPublisher) {
        return new DefaultSingleChatApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * ??????????????????Bean
     */
    @Bean
    @Lazy
    public AllMemberPushApi allMemberPushApi(ImServerProperties imServerProperties,
                                             UserSignService userSignService,
                                             ApplicationEventPublisher eventPublisher) {
        return new DefaultAllMemberPushApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * ??????????????????Bean
     */
    @Bean
    @Lazy
    public PortraitManageApi portraitManageApi(ImServerProperties imServerProperties,
                                               UserSignService userSignService,
                                               ApplicationEventPublisher eventPublisher) {
        return new DefaultPortraitManageApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * ???????????????Bean
     */
    @Bean
    @Lazy
    public RelationChainManageApi relationChainManageApi(ImServerProperties imServerProperties,
                                                         UserSignService userSignService,
                                                         ApplicationEventPublisher eventPublisher) {
        return new DefaultRelationChainApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * ?????????????????????Bean
     */
    @Bean
    @Lazy
    public RecentContactsApi recentContactsApi(ImServerProperties imServerProperties,
                                               UserSignService userSignService,
                                               ApplicationEventPublisher eventPublisher) {
        return new DefaultRecentContactsApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * ??????????????????Bean
     */
    @Bean
    @Lazy
    public GroupManageApi groupManageApi(ImServerProperties imServerProperties,
                                         UserSignService userSignService,
                                         ApplicationEventPublisher eventPublisher) {
        return new DefaultGroupManageApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * ????????????????????????
     */
    @Bean
    @Lazy
    public GlobalMuteManageApi globalMuteManageApi(ImServerProperties imServerProperties,
                                                   UserSignService userSignService,
                                                   ApplicationEventPublisher eventPublisher) {
        return new DefaultGlobalMuteManageApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * ??????????????????Bean
     */
    @Bean
    @Lazy
    public OperationManageApi operationManageApi(ImServerProperties imServerProperties,
                                                 UserSignService userSignService,
                                                 ApplicationEventPublisher eventPublisher) {
        return new DefaultOperationManageApi(imServerProperties, userSignService, eventPublisher);
    }

    /**
     * ??????UserSign???????????????
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
     * ?????????????????????????????????
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setAccountManageWebMapping(RequestMappingHandlerMapping mapping,
                                      AccountManageController controller) throws NoSuchMethodException, SecurityException {

        // ??????????????????
        Method accountImportMethod = AccountManageController.class.getMethod("accountImport", AccountImportParam.class);
        RequestMappingInfo accountImportMapping = RequestMappingInfo
                .paths(BASE_PATH + "/im_open_login_svc/account_import")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(accountImportMapping, controller, accountImportMethod);

        // ??????????????????
        Method multiAccountImportMethod = AccountManageController.class.getMethod("multiAccountImport", MultiAccountImportParam.class);
        RequestMappingInfo multiAccountImportMapping = RequestMappingInfo
                .paths(BASE_PATH + "/im_open_login_svc/multiaccount_import")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(multiAccountImportMapping, controller, multiAccountImportMethod);

        // ????????????
        Method accountDeleteMethod = AccountManageController.class.getMethod("accountDelete", AccountDeleteParam.class);
        RequestMappingInfo accountDeleteMapping = RequestMappingInfo
                .paths(BASE_PATH + "/im_open_login_svc/account_delete")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(accountDeleteMapping, controller, accountDeleteMethod);

        // ????????????????????????IM
        Method accountCheckImportMethod = AccountManageController.class.getMethod("accountCheckImport", AccountCheckParam.class);
        RequestMappingInfo accountCheckImportMapping = RequestMappingInfo
                .paths(BASE_PATH + "/im_open_login_svc/account_check")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(accountCheckImportMapping, controller, accountCheckImportMethod);

        // ????????????????????????
        Method kickMethod = AccountManageController.class.getMethod("kick", KickParam.class);
        RequestMappingInfo kickMapping = RequestMappingInfo
                .paths(BASE_PATH + "/im_open_login_svc/kick")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(kickMapping, controller, kickMethod);

        // ????????????????????????
        Method queryOnlineStatusMethod = AccountManageController.class.getMethod("queryOnlineStatus", QueryOnlineStatusParam.class);
        RequestMappingInfo queryOnlineStatusMapping = RequestMappingInfo
                .paths(BASE_PATH + "/openim/query_online_status")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(queryOnlineStatusMapping, controller, queryOnlineStatusMethod);
    }

    /**
     * ?????????????????????????????????
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setAccountManageWebMapping(RequestMappingHandlerMapping mapping,
                                           SingleChatController controller) throws NoSuchMethodException, SecurityException {

        // ??????????????????
        Method sendMsgMethod = SingleChatController.class.getMethod("sendMsg", SingleIssueSingleChatParam.class);
        RequestMappingInfo sendMsgMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openim/sendmsg")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(sendMsgMappingInfo, controller, sendMsgMethod);

        // ?????????????????????
        Method batchSendMsgMethod = SingleChatController.class.getMethod("batchSendMsg", BatchSingleChatParam.class);
        RequestMappingInfo batchSendMsgMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openim/batchsendmsg")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(batchSendMsgMappingInfo, controller, batchSendMsgMethod);

        // ??????????????????
        Method importMsgMethod = SingleChatController.class.getMethod("importMsg", ImportSingleChatParam.class);
        RequestMappingInfo importMsgMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openim/importmsg")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(importMsgMappingInfo, controller, importMsgMethod);

        // ??????????????????
        Method adminGetroamMsgMethod = SingleChatController.class.getMethod("admin_getroammsg", QuerySingleChatParam.class);
        RequestMappingInfo adminGetroamMsgMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openim/admin_getroammsg")
                .methods(RequestMethod.POST).build();

        mapping.registerMapping(adminGetroamMsgMappingInfo, controller, adminGetroamMsgMethod);

        // ??????????????????
        Method adminMsgWithdrawMethod = SingleChatController.class.getMethod("admin_msgwithdraw", WithdrawSingleChatParam.class);
        RequestMappingInfo adminMsgWithdrawMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openim/admin_msgwithdraw")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(adminMsgWithdrawMappingInfo, controller, adminMsgWithdrawMethod);

        // ????????????????????????
        Method adminSetMsgReadMethod = SingleChatController.class.getMethod("admin_set_msg_read", ReadSingleChatParam.class);
        RequestMappingInfo adminSetMsgReadMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openim/admin_set_msg_read")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(adminSetMsgReadMappingInfo, controller, adminSetMsgReadMethod);

        // ??????????????????????????????
        Method getC2cUnreadMsgNumMethod = SingleChatController.class.getMethod("get_c2c_unread_msg_num", C2CUnreadMsgNumParam.class);
        RequestMappingInfo getC2cUnreadMsgNumMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openim/get_c2c_unread_msg_num")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(getC2cUnreadMsgNumMappingInfo, controller, getC2cUnreadMsgNumMethod);
    }

    /**
     * ??????????????????web??????
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setAllPushWebMapping(RequestMappingHandlerMapping mapping,
                                     AllMemberPushController controller) throws NoSuchMethodException, SecurityException {

    }

    /**
     * ??????????????????web??????
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setPortraitManageWebMapping(RequestMappingHandlerMapping mapping,
                                      PortraitController controller) throws NoSuchMethodException, SecurityException {

        // ????????????
        Method portraitSetMethod = PortraitController.class.getMethod("portrait_set", PortraitSetParam.class);
        RequestMappingInfo portraitSetMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/profile/portrait_set")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(portraitSetMappingInfo, controller, portraitSetMethod);

        // ????????????
        Method portraitGetMethod = PortraitController.class.getMethod("portrait_get", PortraitGetParam.class);
        RequestMappingInfo portraitGetMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/profile/portrait_get")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(portraitGetMappingInfo, controller, portraitGetMethod);
    }

    /**
     * ???????????????web??????
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setRelationChainWebMapping(RequestMappingHandlerMapping mapping,
                                     RelationChainController controller) throws NoSuchMethodException, SecurityException {

        // ????????????
        Method friendAddMethod = RelationChainController.class.getMethod("friendAdd", FriendAddParam.class);
        RequestMappingInfo friendAddMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_add")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendAddMappingInfo, controller, friendAddMethod);

        // ????????????
        Method friendImportMethod = RelationChainController.class.getMethod("friendImport", FriendImportParam.class);
        RequestMappingInfo friendImportMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_import")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendImportMappingInfo, controller, friendImportMethod);

        // ????????????
        Method friendUpdateMethod = RelationChainController.class.getMethod("friendUpdate", FriendUpdateParam.class);
        RequestMappingInfo friendUpdateMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_update")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendUpdateMappingInfo, controller, friendUpdateMethod);

        // ????????????
        Method friendDeleteMethod = RelationChainController.class.getMethod("friendDelete", FriendDeleteParam.class);
        RequestMappingInfo friendDeleteMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_delete")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendDeleteMappingInfo, controller, friendDeleteMethod);

        // ??????????????????
        Method friendDeleteAllMethod = RelationChainController.class.getMethod("friendDeleteAll", FriendDeleteAllParam.class);
        RequestMappingInfo friendDeleteAllMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_delete_all")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendDeleteAllMappingInfo, controller, friendDeleteAllMethod);

        // ????????????
        Method friendCheckMethod = RelationChainController.class.getMethod("friendCheck", FriendCheckParam.class);
        RequestMappingInfo friendCheckMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_check")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendCheckMappingInfo, controller, friendCheckMethod);

        // ????????????
        Method friendGetMethod = RelationChainController.class.getMethod("friendGet", FriendGetParam.class);
        RequestMappingInfo friendGetMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_get")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendGetMappingInfo, controller, friendGetMethod);

        // ?????????????????????????????????
        Method friendGetListMethod = RelationChainController.class.getMethod("friendGetList", FriendGetListParam.class);
        RequestMappingInfo friendGetListMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/friend_get_list")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(friendGetListMappingInfo, controller, friendGetListMethod);

        // ???????????????
        Method blackListAddMethod = RelationChainController.class.getMethod("blackListAdd", BlackListAddParam.class);
        RequestMappingInfo blackListAddMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/black_list_add")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(blackListAddMappingInfo, controller, blackListAddMethod);

        // ???????????????
        Method blackListDeleteMethod = RelationChainController.class.getMethod("blackListDelete", BlackListDeleteParam.class);
        RequestMappingInfo blackListDeleteMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/black_list_delete")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(blackListDeleteMappingInfo, controller, blackListDeleteMethod);

        // ???????????????
        Method blackListGetMethod = RelationChainController.class.getMethod("blackListGet", BlackListGetParam.class);
        RequestMappingInfo blackListGetMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/black_list_get")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(blackListGetMappingInfo, controller, blackListGetMethod);

        // ???????????????
        Method blackListCheckMethod = RelationChainController.class.getMethod("blackListCheck", BlackListCheckParam.class);
        RequestMappingInfo blackListCheckMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/black_list_check")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(blackListCheckMappingInfo, controller, blackListCheckMethod);

        // ????????????
        Method groupAddMethod = RelationChainController.class.getMethod("groupAdd", GroupAddParam.class);
        RequestMappingInfo groupAddMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/group_add")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(groupAddMappingInfo, controller, groupAddMethod);

        // ????????????
        Method groupDeleteMethod = RelationChainController.class.getMethod("groupDelete", GroupDeleteParam.class);
        RequestMappingInfo groupDeleteMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/group_delete")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(groupDeleteMappingInfo, controller, groupDeleteMethod);

        // ????????????
        Method groupGetMethod = RelationChainController.class.getMethod("groupGet", GroupGetParam.class);
        RequestMappingInfo groupGetMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/sns/group_get")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(groupGetMappingInfo, controller, groupGetMethod);
    }

    /**
     * ????????????????????????????????????
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setRecentContactsWebMapping(RequestMappingHandlerMapping mapping,
                                           RecentContactsController controller) throws NoSuchMethodException, SecurityException {

        // ??????????????????
        Method getListMethod = RecentContactsController.class.getMethod("getList", GetListParam.class);
        RequestMappingInfo getListMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/recentcontact/get_list")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(getListMappingInfo, controller, getListMethod);

        // ????????????
        Method deleteSessionMethod = RecentContactsController.class.getMethod("deleteSession", DeleteSessionParam.class);
        RequestMappingInfo deleteSessionMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/recentcontact/delete")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(deleteSessionMappingInfo, controller, deleteSessionMethod);
    }

    /**
     * ?????????????????????????????????
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setGroupManageWebMapping(RequestMappingHandlerMapping mapping,
                                      GroupManageController controller) throws NoSuchMethodException, SecurityException {

    }

    /**
     * ???????????????????????????????????????
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setGlobalMuteWebMapping(RequestMappingHandlerMapping mapping,
                                      GlobalMuteController controller) throws NoSuchMethodException, SecurityException {

        // ??????????????????
        Method setnospeaking = GlobalMuteController.class.getMethod("setnospeaking", SetNoSpeakingParam.class);
        RequestMappingInfo setNoSpeakingMappingInfo = RequestMappingInfo.paths(BASE_PATH + "/openconfigsvr/setnospeaking")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(setNoSpeakingMappingInfo, controller, setnospeaking);

        // ??????????????????
        Method getnospeaking = GlobalMuteController.class.getMethod("getnospeaking", GetNoSpeakingParam.class);
        RequestMappingInfo getNoSpeakingMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openconfigsvr/getnospeaking")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(getNoSpeakingMappingInfo, controller, getnospeaking);
    }

    /**
     * ?????????????????????????????????
     */
    @Autowired(required = false)
    @ConditionalOnBean(RequestMappingHandlerMapping.class)
    public void setOperationManageWebMapping(RequestMappingHandlerMapping mapping,
                                             OperationManageController controller) throws NoSuchMethodException, SecurityException {

        // ??????????????????
        Method getAppInfo = OperationManageController.class.getMethod("getAppInfo", GetAppInfoParam.class);
        RequestMappingInfo getAppInfoMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/openconfigsvr/getappinfo")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(getAppInfoMappingInfo, controller, getAppInfo);

        // ????????????????????????
        Method getHistoryMsg = OperationManageController.class.getMethod("getHistoryMsg", GetHistoryMsgParam.class);
        RequestMappingInfo getHistoryMsgMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/open_msg_svc/get_history")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(getHistoryMsgMappingInfo, controller, getHistoryMsg);

        // ???????????????IP??????
        Method getIpList = OperationManageController.class.getMethod("getIpList");
        RequestMappingInfo getIpListMappingInfo = RequestMappingInfo
                .paths(BASE_PATH + "/ConfigSvc/GetIPList")
                .methods(RequestMethod.POST).build();
        mapping.registerMapping(getIpListMappingInfo, controller, getIpList);
    }
}
