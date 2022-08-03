package io.github.liuruinian.jpush.service;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.connection.HttpProxy;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.DefaultResult;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.AliasDeviceListResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.common.base.Preconditions;
import io.github.liuruinian.jpush.domain.PushMessage;
import io.github.liuruinian.jpush.properties.JPushProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author liuruinian
 * <p>
 *     极光推送API
 * </p>
 */
@Slf4j
public class JPushApi {

    /** 极光配置 */
    private final JPushProperties properties;

    /** 极光客户端 */
    private JPushClient             client;

    public JPushApi(JPushProperties properties) {
        this.properties = properties;
    }

    /**
     * init JPushClient
     */
    public void initClient() {
        Preconditions.checkArgument(StringUtils.isNotEmpty(properties.getAppKey()), "appKey为空!");
        Preconditions.checkArgument(StringUtils.isNotEmpty(properties.getMasterSecret()), "masterSecret为空!");

        ClientConfig clientConfig = ClientConfig.getInstance();
        HttpProxy proxy = null;
        if (properties.getUseProxy()) {
            Preconditions.checkArgument(StringUtils.isNotEmpty(properties.getProxyHost()), "代理服务器主机名或IP为空");
            Preconditions.checkArgument(properties.getProxyPort() > 1, "代理服务器主机端口无效");
            if (StringUtils.isNotEmpty(properties.getProxyUsername())) {
                proxy = new HttpProxy(StringUtils.trim(properties.getProxyHost()), properties.getProxyPort()
                        , StringUtils.trim(properties.getProxyUsername()), StringUtils.trim(properties.getProxyPassword()));
            } else {
                proxy = new HttpProxy(StringUtils.trim(properties.getProxyHost()), properties.getProxyPort());
            }
        }
        client = new JPushClient(properties.getMasterSecret(), properties.getAppKey(), proxy, clientConfig);
        log.info("初始化JPushApi成功!");
    }

    /**
     * 根据设备ID推送
     *
     * @param registrationIdList 设备Registration ID列表
     * @param pm                 消息
     * @return 成功时返回消息ID
     */
    public Long pushToDevices(List<String> registrationIdList, PushMessage pm) {
        Preconditions.checkArgument(!CollectionUtils.isEmpty(registrationIdList), "设备Registration ID为空");
        Preconditions.checkArgument(registrationIdList.size() <= 1000, "设备Registration ID不超过1000个");
        return push(createPushPayload(pm, Audience.registrationId(registrationIdList)));
    }


    /**
     * 根据别名推送
     *
     * @param aliasList 别名列表
     * @param pm        消息
     * @return 成功时返回消息ID
     */
    public Long pushToAliases(List<String> aliasList, PushMessage pm) {
        Preconditions.checkArgument(!CollectionUtils.isEmpty(aliasList), "别名为空");
        Preconditions.checkArgument(aliasList.size() <= 1000, "别名不超过1000个");
        return push(createPushPayload(pm, Audience.alias(aliasList)));
    }


    /**
     * 根据标签推送
     *
     * @param tagsList 标签列表
     * @param pm       消息
     * @return 成功时返回消息ID
     */
    public Long pushToTags(List<String> tagsList, PushMessage pm) {
        Preconditions.checkArgument(!CollectionUtils.isEmpty(tagsList), "标签为空");
        Preconditions.checkArgument(tagsList.size() <= 1000, "标签不超过1000个");
        return push(createPushPayload(pm, Audience.tag(tagsList)));
    }


    /**
     * 推送给所有客户端
     *
     * @param pm 消息
     * @return 成功时返回消息ID
     */
    public Long pushToAll(PushMessage pm) {
        return push(createPushPayload(pm, Audience.all()));
    }


    /**
     * 查询指定别名下的设备Registration ID
     *
     * @param alias 待查询的别名
     * @return 关联的设备Registration ID列表
     */
    public List<String> findRegistrationId(String alias) {
        if (StringUtils.isEmpty(alias)) {
            return null;
        }
        try {
            AliasDeviceListResult result = client.getAliasDeviceList(alias, null);
            if (result == null || 200 != result.getResponseCode()) {
                return null;
            }
            return result.registration_ids;
        } catch (APIConnectionException | APIRequestException e) {
            log.error("查询别名关联的设备时发生异常! alias:{}", alias, e);
        }
        return null;
    }


    /**
     * 删除别名
     *
     * @param aliasList 待删除的别名列表
     */
    public void deleteAlias(List<String> aliasList) {
        if (CollectionUtils.isEmpty(aliasList)) {
            return;
        }
        aliasList.forEach(a -> {
            if (StringUtils.isEmpty(a)) {
                return;
            }
            try {
                DefaultResult result = client.deleteAlias(a, null);
                log.info("删除别名接口返回码: {}, alias: {}", result != null ? result.getResponseCode() : null, a);
            } catch (APIConnectionException | APIRequestException e) {
                log.error("删除别名时发生异常! alias:{}", a, e);
            }
        });
    }


    /**
     * 进行推送
     *
     * @param payload 消息体
     * @return 成功时返回消息ID
     */
    private Long push(PushPayload payload) {
        if (properties.getRetryMaxAttempts() != null && properties.getRetryMaxAttempts() > 0) {
            try {
                return pushWithRetry(payload);
            } catch (Exception e) {
                log.error("推送时发生异常", e);
            }
            return null;
        } else {
            return executePush(payload);
        }
    }

    /**
     * 进行推送(支持重试)
     *
     * @param payload 消息体
     * @return 成功时返回消息ID
     */
    @SuppressWarnings("BusyWait")
    private Long pushWithRetry(PushPayload payload) throws Exception {
        Long msgId = executePush(payload);
        if (msgId == null) {
            long sleepTime = properties.getRetryInterval() > 0 ? properties.getRetryInterval() : 500L;
            for (int i = 0; i < properties.getRetryMaxAttempts(); i++) {
                Thread.sleep(sleepTime);
                msgId = executePush(payload);
                if (msgId != null) {
                    break;
                }
                sleepTime *= 2;
            }
        }
        return msgId;
    }

    /**
     * 执行推送
     *
     * @param payload 消息体
     * @return 成功时返回消息ID
     */
    private Long executePush(PushPayload payload) {
        log.info("推送消息体: {}", payload.toJSON());
        try {
            PushResult result = client.sendPush(payload);
            if (result == null) {
                return null;
            }
            if (200 == result.getResponseCode()) {
                log.info("消息推送成功，消息ID: {}", result.msg_id);
                return result.msg_id;
            }
            log.error("消息推送失败，ResponseCode={}", result.getResponseCode());
        } catch (APIConnectionException e) {
            log.error("推送消息出现APIConnectionException异常", e);
        } catch (APIRequestException e) {
            log.error("推送消息出现APIRequestException异常", e);
            log.error("推送消息出现异常, status={}, errorCode={}, errorMessage={}", e.getStatus(), e.getErrorCode(), e.getErrorMessage());
        }
        return null;
    }

    /**
     * 构建一个 PushPayload 对象
     * @param pm 推送消息
     * @param audience 接收方
     */
    private PushPayload createPushPayload(PushMessage pm, Audience audience) {
        Preconditions.checkArgument(pm != null, "消息为空!");
        Preconditions.checkArgument(StringUtils.isNotEmpty(pm.getContent()), "消息内容为空!");
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(audience)
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setSound("default")
                                .setAlert(IosAlert.newBuilder()
                                        .setTitleAndBody(pm.getTitle(), "", pm.getContent())
                                        .build())
                                .setBadge(pm.getBadge() != null ? pm.getBadge() : 1)
                                .addExtras(pm.getExtras())
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(pm.getTitle())
                                .setAlert(StringUtils.trim(pm.getContent()))
                                .setBuilderId(1)
                                .setPriority(pm.getPriority() != null ? pm.getPriority() : 0)
                                .addExtras(pm.getExtras())
                                .build())
                        .build()
                ).build();
    }

    /**
     * 自定义消息
     *
     * @param pm       推送消息
     * @param aliasList 接收方
     */
    public Long createCustomPushMessage(List<String> aliasList,PushMessage pm) {
        Preconditions.checkArgument(pm != null, "消息为空!");
        Preconditions.checkArgument(StringUtils.isNotEmpty(pm.getContent()), "消息内容为空!");
        return push(PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(aliasList))
                .setOptions(Options.newBuilder().setApnsProduction(true).build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(pm.getContent())
                        .addExtras(pm.getExtras())
                        .build())
                .build());
    }

}
