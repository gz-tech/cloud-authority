package com.upuphone.cloudplatform.authority.vo.response.rolepermission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@ApiModel(value = "已授权列表 response")
@Getter
@Setter
public class AuthorizedRoleResponse {
    @ApiModelProperty(value = "已授权列表")
    private List<AuthorizedRoleVo> authorizedRoleVoList;
}
