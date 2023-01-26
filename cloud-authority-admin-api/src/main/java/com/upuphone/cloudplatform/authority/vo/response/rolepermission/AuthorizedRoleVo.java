package com.upuphone.cloudplatform.authority.vo.response.rolepermission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @Classname RoleResourceListVo
 * @Description
 * @Date 2022/3/23 4:54 下午
 * @Created by gz-d
 */
@ApiModel(value = "已授权角色 response")
@Getter
@Setter
public class AuthorizedRoleVo {
    @ApiModelProperty("角色id")
    private String authRoleId;

    @ApiModelProperty("角色code")
    private String authRoleCode;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
