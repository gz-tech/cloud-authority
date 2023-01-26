package com.upuphone.cloudplatform.authority.business.service.basic.repo;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.upuphone.cloudplatform.authority.common.constants.SysRelationEnum;
import com.upuphone.cloudplatform.authority.common.constants.SysRoleEnum;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysRoleUserPo;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysUserResourcePo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysRoleUserMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysUserResourceMapper;
import com.upuphone.cloudplatform.common.utils.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AddDeveloperRepo {

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysUserResourceMapper sysUserResourceMapper;


    @Transactional(timeout = 10)
    public int checkRoleAndDevlprToSys(String developerId, Long systemId, SysRoleEnum sysRole) {
        return addRoleUser(developerId, systemId, sysRole);
//        return addDeveloperSystemRelation(developerId, systemId);
    }

    public int addRoleUser(String developerId, Long systemId, SysRoleEnum sysRole) {
        List<SysRoleUserPo> sysRoleUserPos = sysRoleUserMapper.selectList(Wrappers.
                <SysRoleUserPo>lambdaQuery()
                .eq(SysRoleUserPo::getRoleType, sysRole.getRoleType())
                .eq(SysRoleUserPo::getUserId, developerId)
                .eq(SysRoleUserPo::getSystemId, systemId));

        if (ListUtil.any(sysRoleUserPos)) {
            return 0;
        }
        SysRoleUserPo sysRoleUserPo = new SysRoleUserPo();
        sysRoleUserPo.setUserId(developerId);
        sysRoleUserPo.setRoleType(sysRole.getRoleType());
        sysRoleUserPo.setSystemId(systemId);
        return sysRoleUserMapper.insert(sysRoleUserPo);
    }

    public int addDeveloperSystemRelation(String developerId, Long systemId) {
        SysUserResourcePo sysUserResourcePo = new SysUserResourcePo();
        sysUserResourcePo.setId(IdWorker.getId());
        sysUserResourcePo.setResourceId(systemId);
        sysUserResourcePo.setUserId(developerId);
        sysUserResourcePo.setRelationType(SysRelationEnum.SYSTEM_DEVELOPER.getType());
        return sysUserResourceMapper.insert(sysUserResourcePo);
    }
}
