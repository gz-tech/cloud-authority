package com.upuphone.cloudplatform.authority.business.service.system;

import com.upuphone.cloudplatform.authority.mybatis.entity.SysRoleUserPo;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysUserResourcePo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysRoleUserMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysUserResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SystemUtil {

    @Autowired
    private SysUserResourceMapper sysUserResourceMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    public void addUserRole(String userId, Integer roleType, Long systemId) {
        SysRoleUserPo sysRoleUserPo = new SysRoleUserPo();
        sysRoleUserPo.setRoleType(roleType);
        sysRoleUserPo.setUserId(userId);
        sysRoleUserPo.setSystemId(systemId);
        sysRoleUserMapper.insert(sysRoleUserPo);
    }

    public void addUserResource(String userId, Long systemId, Integer relationType) {
        SysUserResourcePo sysUserResourcePo = new SysUserResourcePo();
        sysUserResourcePo.setUserId(userId);
        sysUserResourcePo.setResourceId(systemId);
        sysUserResourcePo.setRelationType(relationType);
        sysUserResourceMapper.insert(sysUserResourcePo);
    }
}
