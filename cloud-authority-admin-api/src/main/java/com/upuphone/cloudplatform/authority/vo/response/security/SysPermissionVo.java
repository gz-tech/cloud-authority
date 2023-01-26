package com.upuphone.cloudplatform.authority.vo.response.security;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("内部权限VO")
public class SysPermissionVo {

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("类型 1-路由 2-菜单")
    private Integer type;

}
