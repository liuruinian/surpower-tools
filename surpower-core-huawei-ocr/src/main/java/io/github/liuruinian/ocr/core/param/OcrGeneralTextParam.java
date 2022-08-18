package io.github.liuruinian.ocr.core.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author admin
 */
@Data
@ApiModel("通用文字识别参数")
public class OcrGeneralTextParam {
    /**
     * 该参数与url二选一。
     * <p>
     * 图片的Base64编码，要求Base64编码后大小不超过10MB。
     * <p>
     * 图片最短边不小于15px，最长边不超过8000px。支持JPEG、JPG、PNG、BMP、TIFF格式。
     * <p>
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
     * 是否校正图片的倾斜角度，可选值如下。
     * <p>
     * true：校正图片的倾斜角度
     * false：不校正图片的倾斜角度
     * 支持任意角度的校正，未传入该参数时默认为“false”。
     * <p>
     * 待识别图片如果存在倾斜，建议将此参数设置为“true”。
     */
    @ApiModelProperty(name = "detect_direction", value = "detect_direction", required = false)
    @JsonProperty(value = "detect_direction")
    @JSONField(name = "detect_direction")
    private Boolean detect_direction;

    /**
     * 快速模式开关，针对单行文字图片（要求图片只包含一行文字，且文字区域占比超过50%），打开时可以更快返回识别内容。可选值如下所示。
     * <p>
     * true：打开快速模式
     * false：关闭快速模式
     * 未传入该参数时默认为false，即关闭快速模式。
     */
    @ApiModelProperty(name = "quick_mode", value = "quick_mode", required = false)
    @JsonProperty(value = "quick_mode")
    @JSONField(name = "quick_mode")
    private Boolean quick_mode;

}
