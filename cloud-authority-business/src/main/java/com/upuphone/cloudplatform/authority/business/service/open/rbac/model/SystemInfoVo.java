package com.upuphone.cloudplatform.authority.business.service.open.rbac.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemInfoVo {
    private Long systemId;
    private String systemName;
    private String systemCode;
    private Long ownerUserId;
    private String ownerUserName;
}
