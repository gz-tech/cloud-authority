package com.upuphone.cloudplatform.authority.api.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("请求基类request")
public class BaseRequest {

    @ApiModelProperty(value = "system_code", required = true)
    @NotBlank
    private String systemCode;

    @ApiModelProperty(value = "secret", required = true)
    // @NotBlank
    private String secret;
}
