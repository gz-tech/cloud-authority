package com.upuphone.cloudplatform.authority.vo.response.security;

import com.upuphone.cloudplatform.authority.vo.response.user.UserDetailVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("内部用户含权限信息VO")
public class SysUserDetailVo extends UserDetailVo {

    @ApiModelProperty("角色名列表")
    private Set<String> roles;

    @ApiModelProperty("权限列表")
    private Set<SysPermissionVo> permissionList;

}
