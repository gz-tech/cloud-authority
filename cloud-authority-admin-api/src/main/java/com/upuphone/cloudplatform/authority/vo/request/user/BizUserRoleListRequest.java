package com.upuphone.cloudplatform.authority.vo.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("用户角色列表request")
public class BizUserRoleListRequest {

    @ApiModelProperty(value = "用户ID", required = true)
    @NotEmpty
    private String id;

    @ApiModelProperty(value = "系统ID", required = true)
    @NotEmpty
    private String systemId;

}
