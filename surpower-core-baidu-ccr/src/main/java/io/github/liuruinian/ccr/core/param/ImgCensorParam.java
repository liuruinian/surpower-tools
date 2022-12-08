package io.github.liuruinian.ccr.core.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * img censor param
 *
 * @author liuruinian
 * @version 2022-12-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("图像审核请求参数")
public class ImgCensorParam implements Serializable {

    @JsonProperty(value = "image")
    @JSONField(name = "image")
    @ApiModelProperty(name = "", value = "待审核图像Base64编码字符串，和imgUrl二选一, \n" +
            "以图像文件形式请求时必填，\n" +
            "图像要求base64后\n" +
            "大于等于5kb，小于等于4M，\n" +
            "最短边大于等于128像素，\n" +
            "小于等于4096像素，\n" +
            "支持的图片格式：PNG、JPG、JPEG、BMP、GIF（仅对首帧进行审核）、Webp、TIFF")
    private String image;

    @JsonProperty(value = "imgUrl")
    @JSONField(name = "imgUrl")
    @ApiModelProperty(name = "imgUrl", value = "图像URL地址, 和image二选一，\n" +
            "以URL形式请求，\n" +
            "图像Url需要做UrlEncode，\n" +
            "图像要求base64后大于等于5kb，\n" +
            "小于等于4M，\n" +
            "最短边大于等于128像素，\n" +
            "小于等于4096像素\n" +
            "支持的图片格式：PNG、JPG、JPEG、BMP、GIF（仅对首帧进行审核）、Webp、TIFF")
    private String imgUrl;

    @JsonProperty(value = "imgType")
    @JSONField(name = "imgType")
    @ApiModelProperty(name = "imgType", value = "静态图片（PNG、JPG、JPEG、BMP、GIF（仅对首帧进行审核）、Webp、TIFF），1:GIF动态图片")
    private Integer imgType;
}
