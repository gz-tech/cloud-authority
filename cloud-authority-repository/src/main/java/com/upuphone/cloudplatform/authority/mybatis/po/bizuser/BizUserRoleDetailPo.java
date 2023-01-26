package com.upuphone.cloudplatform.authority.mybatis.po.bizuser;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BizUserRoleDetailPo {

    /**
     * 业务角色用户关联关系ID(biz_role_user.id)
     */
    private Long id;

    private String userId;

    private String code;

    private String description;

    private Long systemId;

    private LocalDateTime createTime;

}
