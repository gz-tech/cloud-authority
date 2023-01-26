package com.upuphone.cloudplatform.authority.mybatis.po.bizpermission;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthorizedRoleListPo {

    private String authRoleId;

    private String authRoleCode;

    private LocalDateTime updateTime;
}
