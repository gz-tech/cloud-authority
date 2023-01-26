package com.upuphone.cloudplatform.authority.vo.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("用户角色列表response")
public class BizUserRoleListResponse {

    @ApiModelProperty("用户角色列表")
    private List<UserRoleDetailVo> list;

}
