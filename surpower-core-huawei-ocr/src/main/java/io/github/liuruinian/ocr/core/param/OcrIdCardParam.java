package io.github.liuruinian.ocr.core.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("身份证识别请求参数")
public class OcrIdCardParam implements Serializable {

    /**
     * 该参数与url二选一。
     *
     * 图片的Base64编码，要求Base64编码后大小不超过10MB。
     *
     * 图片最短边不小于15px，最长边不超过8000px。支持JPEG、JPG、PNG、BMP、TIFF格式。
     *
     * 图片Base64编码示例如/9j/4AAQSkZJRgABAg...，带有多余前缀会产生The image format is not supported报错。
     */
    @ApiModelProperty(name = "image", value = "image")
    @JsonProperty(value = "image")
    @JSONField(name = "image")
    private String image;

    /**
     * 该参数与image二选一。图片的url路径，目前支持：
     * 1.公网http/https url
     * 2.OBS提供的url，使用OBS数据需要进行授权。包括对服务授权、临时授权、匿名公开授权
     */
    @ApiModelProperty(name = "url", value = "url")
    @JsonProperty(value = "url")
    @JSONField(name = "url")
    private String url;

    /**
     * front：身份证人像面
     * back：身份证国徽面
     * 如果参数值为空或无该参数，系统自动识别，建议填写，准确率更高。
     */
    @ApiModelProperty(name = "side", value = "side")
    @JsonProperty(value = "side")
    @JSONField(name = "side")
    private String side;

    /**
     * 返回校验身份证号等信息的开关，默认false，可选值如下所示：
     * true：返回校验信息
     * false：不返回校验信息
     */
    @ApiModelProperty(name = "returnVerification", value = "returnVerification")
    @JsonProperty(value = "return_verification")
    @JSONField(name = "return_verification")
    private Boolean returnVerification;

    /**
     * 识别到的文字块的区域位置信息。可选值包括：
     *
     * true：返回各个文字块区域
     *
     * false：不返回各个文字块区域
     *
     * 如果无该参数，系统默认不返回文字块区域。如果输入参数不是Boolean类型，则会报非法参数错误.
     */
    @ApiModelProperty(name = "returnTextLocation", value = "returnTextLocation")
    @JsonProperty(value = "return_text_location")
    @JSONField(name = "return_text_location")
    private Boolean returnTextLocation;
}
