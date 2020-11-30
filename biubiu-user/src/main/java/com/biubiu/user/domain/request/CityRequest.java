package com.biubiu.user.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * CityRequest
 *
 * @author wbbaijq
 */
@ApiModel
@Data
public class CityRequest {

    @ApiModelProperty(value = "城市", example = "上海")
    private String city;

}
