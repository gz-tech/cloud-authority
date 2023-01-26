package com.upuphone.cloudplatform.authority.vo.response.rolepermission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "权限 response")
@Getter
@Setter
public class PermissionListVo {

    @ApiModelProperty("权限id")
    private String id;

    @ApiModelProperty("权限名")
    private String name;



}
