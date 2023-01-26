package com.upuphone.cloudplatform.authority.mybatis.po.bizpermission;

import lombok.Data;

@Data
public class BizPermissionListPo {
    /**
     * 权限id
     */
    private String id;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 授权类型 1-query
     */
    private Integer action;

    private String resourceDescp;

    private String resourcePath;

    private String resourceType;
}
