package com.upuphone.cloudplatform.authority.vo.response.roleresource;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @Classname RoleResourceListVo
 * @Description
 * @Date 2022/3/23 4:54 下午
 * @Created by gz-d
 */
@ApiModel(value = "")
@Getter
@Setter
public class RoleResourceListVo {
    private String authRoleId;

    private LocalDateTime updateTime;
}
