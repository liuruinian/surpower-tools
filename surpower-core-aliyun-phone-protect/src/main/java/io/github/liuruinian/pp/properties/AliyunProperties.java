package io.github.liuruinian.pp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "phone.protection.aliyun")
public class AliyunProperties {

    /**
     * Endpoint
     */
    private String      endpoint = "cn-hangzhou";

    /**
     * accessKeyId
     */
    private String      accessKeyId;

    /**
     * accessKeySecret
     */
    private String      accessKeySecret;

    /**
     * poolKey
     */
    private String      poolKey;

    private Boolean     enable = true;

    /**
     * 回执消息队列--消息类型
     * <p>
     *     SecretStartReport、SecretReport、SecretSmsIntercept、SecretRecording
     * </p>
     */
    private String      messageType;

    /**
     * 是否启动呼叫发起时话单报告
     */
    private Boolean     enableSecretStartReport = false;

    /**
     * 呼叫发起时话单报告队列名称
     */
    private String      secretStartReportQueueName;

    /**
     * 是否启用呼叫结束后话单报告
     */
    private Boolean     enableSecretReport = false;

    /**
     * 呼叫结束后话单报告队列名称
     */
    private String      secretReportQueueName;

    /**
     * 是否启用录音完成事件消息
     */
    private Boolean     enableSecretRecording = false;

    /**
     * 录音完成事件消息队列名称
     */
    private String      secretRecordingQueueName;


}
