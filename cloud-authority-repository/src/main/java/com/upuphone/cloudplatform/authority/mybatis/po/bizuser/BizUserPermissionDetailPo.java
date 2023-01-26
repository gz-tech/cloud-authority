package com.upuphone.cloudplatform.authority.mybatis.po.bizuser;

import lombok.Data;

@Data
public class BizUserPermissionDetailPo {

    /**
     * biz_role_permission.id
     */
    private Long id;

    /**
     * biz_resource.code
     */
    private String code;

    /**
     * biz_resource.resType
     */
    private Integer resType;

    /**
     * biz_resource.description
     */
    private String description;

    /**
     * biz_resource.path
     */
    private String path;

    /**
     * biz_permission.action
     */
    private Integer action;

}
