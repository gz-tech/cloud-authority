package com.upuphone.cloudplatform.authority.vo.response.rolepermission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "权限详情 response")
@Getter
@Setter
public class PermissionDetailVo {

    @ApiModelProperty("权限id")
    private String id;

    @ApiModelProperty("权限名")
    private String name;

    @ApiModelProperty("资源描述")
    private String resourceDescp;

    @ApiModelProperty("资源path")
    private String resourcePath;

    @ApiModelProperty("资源类型")
    private String resourceType;

}
