package com.upuphone.cloudplatform.authority.vo.response.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class AccessTokenVo {

    @ApiModelProperty("accessToken 放入Authorization头中 格式:'bearer ' + token")
    private String accessToken;

    @ApiModelProperty("refreshToken 预留")
    private String refreshToken;

    @ApiModelProperty("要重定向到的请求地址")
    private String redirectUri;

}
