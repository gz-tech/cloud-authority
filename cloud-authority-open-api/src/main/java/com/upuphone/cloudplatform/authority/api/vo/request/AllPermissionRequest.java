package com.upuphone.cloudplatform.authority.api.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("检查是否允许request")
public class AllPermissionRequest extends BaseRequest {

    @ApiModelProperty(value = "资源类型：API,MENU", required = true)
    private String resourceType;//api,menu

    @ApiModelProperty(value = "操作 类型：QUERY，默认:QUERY", required = false)
    private String action;

    @ApiModelProperty(value = "是否 允许", required = true)
    private Boolean isAllow;


}
