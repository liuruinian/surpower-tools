package io.github.liuruinian.ocr.core.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@ApiModel("车牌识别请求参数")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OcrLicensePlateParam implements Serializable {

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
}
