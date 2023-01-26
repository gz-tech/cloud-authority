package com.upuphone.cloudplatform.authority.vo.request.rolepermission;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "系统权限列表")
@Getter
@Setter
public class BizSystemPermissionListRequest {
    @ApiModelProperty(value = "系统id", required = true)
    @NotBlank(message = "系统id不为空")
    private String systemId;
}
