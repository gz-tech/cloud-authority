package com.upuphone.cloudplatform.authority.vo.response.rolepermission;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel(value = "角色权限详情 response")
@Getter
@Setter
public class RolePermissionDetailResponse {
    private List<PermissionDetailVo> permissionList;


}
