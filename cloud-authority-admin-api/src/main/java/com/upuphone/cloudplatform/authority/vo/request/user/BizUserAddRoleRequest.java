package com.upuphone.cloudplatform.authority.vo.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@ApiModel("用户添加角色request")
public class BizUserAddRoleRequest {

    @ApiModelProperty(value = "角色ID列表", required = true)
    @NotEmpty
    private Set<String> roleIds;

    @ApiModelProperty(value = "用户ID", required = true)
    @NotEmpty
    private String userId;

    @ApiModelProperty(value = "系统ID", required = true)
    @NotEmpty
    private String systemId;

}
