package com.upuphone.cloudplatform.authority.vo.request.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("角色搜索Request")
public class BizRoleListRequest {

    @ApiModelProperty("角色code")
    private String code;

    @ApiModelProperty("角色描述")
    private String description;

    @ApiModelProperty(value = "系统ID", required = true)
    @NotEmpty
    private String systemId;

}
