package io.github.liuruinian.pp.provider.bind;

import com.aliyuncs.dyplsapi.model.v20170525.BindAxnRequest;
import com.aliyuncs.dyplsapi.model.v20170525.BindAxnResponse;
import io.github.liuruinian.pp.domain.AxnBindParam;
import io.github.liuruinian.pp.properties.AliyunProperties;
import io.github.liuruinian.pp.provider.AbstractProvider;
import io.github.liuruinian.pp.service.bind.BindManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Objects;

@Slf4j
public class BindProvider extends AbstractProvider implements BindManager {

    public BindProvider(AliyunProperties properties, ApplicationEventPublisher eventPublisher) {
        super(properties, eventPublisher);
    }

    @Override
    public BindAxnResponse bindAxn(AxnBindParam axnBindParam) {
        // AXN绑定请求结构体-参数说明详见参数说明: https://help.aliyun.com/document_detail/59539.html
        BindAxnRequest request = new BindAxnRequest();

        try {
            // 必填参数校验
            axnBindParam.checkRequiredParam();
            // 必填:号池Key-详见概览页面的号池管理功能
            request.setPoolKey(poolKey);
            // 必填:AXN关系中的A号码
            request.setPhoneNoA(axnBindParam.getPhoneNoA());
            // 必填:绑定关系对应的失效时间-不能早于当前系统时间
            request.setExpiration(axnBindParam.getExpiration());
            // 可选:AXN中A拨打X的时候转接到的默认的B号码,如果不需要则不设置
            if (StringUtils.isNotBlank(axnBindParam.getPhoneNoB())) {
                request.setPhoneNoB(axnBindParam.getPhoneNoB());
            }
            // 可选:指定X号码进行选号
            if (StringUtils.isNotBlank(axnBindParam.getPhoneNoX())) {
                request.setPhoneNoX(axnBindParam.getPhoneNoX());
            }
            // 可选:期望分配X号码归属的地市(省去地市后缀后的城市名称)
            if (StringUtils.isNotBlank(axnBindParam.getExpectCity())) {
                request.setExpectCity(axnBindParam.getExpectCity());
            }
            // 可选:是否需要录制音频-默认是false
            if (Objects.nonNull(axnBindParam.getIsRecordingEnabled())) {
                request.setIsRecordingEnabled(axnBindParam.getIsRecordingEnabled());
            }
            // 可选:号码类型
            if (StringUtils.isNotBlank(axnBindParam.getNoType())) {
                request.setNoType(axnBindParam.getNoType());
            }
            // 可选:外部业务扩展字段，通话记录回执消息中会回传此参数
            if (StringUtils.isNotBlank(axnBindParam.getOutId())) {
                request.setOutId(axnBindParam.getOutId());
            }
            // 可选:外部业务ID
            if (StringUtils.isNotBlank(axnBindParam.getOutOrderId())) {
                request.setOutOrderId(axnBindParam.getOutOrderId());
            }
            // 可选:重置绑定关系中的号码显示逻辑。固定取值：1（主被叫显示中间号码X）
            if (Objects.nonNull(axnBindParam.getCallDisplayType())) {
                request.setCallDisplayType(axnBindParam.getCallDisplayType());
            }
            // 可选:设置AXN分机号绑定关系中的企业彩铃放音编码
            if (StringUtils.isNotBlank(axnBindParam.getRingConfig())) {
                request.setRingConfig(axnBindParam.getRingConfig());
            }
            // 可选:ASR状态
            if (Objects.nonNull(axnBindParam.getAsrStatus())) {
                request.setASRStatus(axnBindParam.getAsrStatus());
            }
            // 可选:ASR模型ID
            if (StringUtils.isNotBlank(axnBindParam.getAsrModelId())) {
                request.setASRModelId(axnBindParam.getAsrModelId());
            }
            // 可选:顺振时长。单位：秒
            if (Objects.nonNull(axnBindParam.getCallTimeout())) {
                request.setCallTimeout(axnBindParam.getCallTimeout());
            }
            // 可选:单通呼叫限制的状态
            if (StringUtils.isNotBlank(axnBindParam.getCallRestrict())) {
                request.setCallRestrict(axnBindParam.getCallRestrict());
            }

            BindAxnResponse response = acsClient.getAcsResponse(request);
            if (response.getCode() != null && OK.equals(response.getCode())) {
                return response;
            }
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("AXN绑定请求异常!", e);
            }
        }

        return null;
    }
}
