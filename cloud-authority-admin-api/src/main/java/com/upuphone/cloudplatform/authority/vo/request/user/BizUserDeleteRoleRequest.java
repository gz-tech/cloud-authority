package com.upuphone.cloudplatform.authority.vo.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("移除用户角色request")
public class BizUserDeleteRoleRequest {

    @ApiModelProperty(value = "关联关系ID", required = true)
    @NotEmpty
    private String id;

    @ApiModelProperty(value = "系统ID", required = true)
    @NotEmpty
    private String systemId;

}
