package com.upuphone.cloudplatform.authority.vo.request.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class AccessTokenRequest {

    @ApiModelProperty(value = "code", required = true)
    private String code;
    @ApiModelProperty(value = "state", required = true)
    private String state;

}
