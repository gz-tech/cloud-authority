package com.upuphone.cloudplatform.authority.vo.request.rolepermission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Classname RoleResourceEditRequest
 * @Description
 * @Date 2022/3/23 4:13 下午
 * @Created by gz-d
 */
@ApiModel(value = "编辑授权")
@Getter
@Setter
public class RolePermissionEditRequest {
    @ApiModelProperty(value = "授权角色", required = true)
    @NotBlank(message = "角色不为空")
    private String authRoleId;

    @ApiModelProperty(value = "权限list", required = true)
    @NotNull(message = "权限不为null")
    private List<String> authPermissionIdList;

    @ApiModelProperty(value = "系统id", required = true)
    private String systemId;
}
