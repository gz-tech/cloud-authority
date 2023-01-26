package com.upuphone.cloudplatform.authority.vo.request.rolepermission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Classname RoleResourceAddRequest
 * @Description
 * @Date 2022/3/23 2:58 下午
 * @Created by gz-d
 */
@ApiModel(value = "新增授权")
@Getter
@Setter
public class RolePermissionAddRequest {
    @ApiModelProperty(value = "授权角色", required = true)
    private List<String> authRoleIdList;

    @ApiModelProperty(value = "权限", required = true)
    private List<String> authPermissionIdList;

    @ApiModelProperty(value = "系统id", required = true)
    @NotBlank(message = "系统id不为空")
    private String systemId;
}
