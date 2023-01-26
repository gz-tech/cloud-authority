package com.upuphone.cloudplatform.authority.vo.request.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("角色修改request")
public class BizRoleModifyRequest {

    @ApiModelProperty(value = "角色ID", required = true)
    @NotEmpty
    private String id;

    @ApiModelProperty(value = "角色code", required = true)
    @NotEmpty
    private String code;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "系统ID", required = true)
    @NotEmpty
    private String systemId;
}
