package com.upuphone.cloudplatform.authority.vo.request.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("删除角色request")
public class BizRoleDeleteRequest {

    @ApiModelProperty(value = "角色ID", required = true)
    @NotEmpty
    private String roleId;

    @ApiModelProperty(value = "系统ID", required = true)
    @NotEmpty
    private String systemId;
}
