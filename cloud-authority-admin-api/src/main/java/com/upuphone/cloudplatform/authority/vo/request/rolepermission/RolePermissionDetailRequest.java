package com.upuphone.cloudplatform.authority.vo.request.rolepermission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("角色绑定权限详情Request")
public class RolePermissionDetailRequest {
    @ApiModelProperty(value = "角色id", required = true)
    @NotBlank(message = "角色id不为空")
    private String roleId;

    @ApiModelProperty(value = "系统id", required = true)
    @NotBlank(message = "系统id不为空")
    private String systemId;
}
