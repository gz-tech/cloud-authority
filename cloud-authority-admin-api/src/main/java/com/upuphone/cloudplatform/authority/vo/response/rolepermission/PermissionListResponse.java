package com.upuphone.cloudplatform.authority.vo.response.rolepermission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel(value = "权限列表 response")
@Getter
@Setter
public class PermissionListResponse {
    @ApiModelProperty("权限列表")

    private List<PermissionListVo> permissionList;
}
