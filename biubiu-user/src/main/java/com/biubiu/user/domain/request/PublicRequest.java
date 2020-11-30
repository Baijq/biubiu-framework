package com.biubiu.user.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * PublicRequest
 *
 * @author wbbaijq
 */
@ApiModel
@Data
public class PublicRequest {

    @ApiModelProperty(value = "请求地址", example = "http://www.tianqiapi.com/api?version=v9&appid=23035354&appsecret=8YvlPNrz")
    private String url;

    @ApiModelProperty(value = "参数", example = "{\"name\":\"tom\"}")
    private String param;

    @ApiModelProperty(value = "请求头", example = "platform=android")
    private String header;
}
