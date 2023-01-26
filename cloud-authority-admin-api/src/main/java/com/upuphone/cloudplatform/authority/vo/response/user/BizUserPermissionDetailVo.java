package com.upuphone.cloudplatform.authority.vo.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("用户权限详情")
public class BizUserPermissionDetailVo {

    @ApiModelProperty("权限ID")
    private String id;
    @ApiModelProperty("资源名称")
    private String code;
    @ApiModelProperty("资源类型 0-菜单 1-接口")
    private Integer type;
    @ApiModelProperty("资源描述")
    private String description;
    @ApiModelProperty("资源URI")
    private String path;

}
