package com.upuphone.cloudplatform.authority.vo.response.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("角色列表response")
public class BizRoleListResponse {

    @ApiModelProperty("角色列表")
    private List<RoleDetailVo> list;

}
