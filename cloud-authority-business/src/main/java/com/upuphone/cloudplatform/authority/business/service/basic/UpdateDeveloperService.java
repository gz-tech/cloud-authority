package com.upuphone.cloudplatform.authority.business.service.basic;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.upuphone.cloudplatform.authority.common.constants.SysRelationEnum;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysRoleUserPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysRoleUserMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysUserResourceMapper;
import com.upuphone.cloudplatform.authority.vo.request.basic.DeveloperUpdateRequest;
import com.upuphone.cloudplatform.authority.vo.response.systembasicinfo.DeveloperUpdateResposne;
import com.upuphone.cloudplatform.common.component.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UpdateDeveloperService extends BaseService<DeveloperUpdateRequest, DeveloperUpdateResposne> {

    @Autowired
    private SysUserResourceMapper sysUserResourceMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    protected void validate(DeveloperUpdateRequest developerUpdateRequest) {

    }

    @Override
    protected DeveloperUpdateResposne processCore(DeveloperUpdateRequest developerUpdateRequest) throws Exception {

        SysRoleUserPo sysRoleUserUpdater = new SysRoleUserPo();
        sysRoleUserMapper.update(sysRoleUserUpdater,Wrappers.<SysRoleUserPo>lambdaUpdate().
                set(SysRoleUserPo::getDelFlag, developerUpdateRequest.isDeleteFlag() ? new Date().getTime()/1000 : 0)
                .eq(SysRoleUserPo::getUserId, developerUpdateRequest.getUserId())
                .eq(SysRoleUserPo::getSystemId, developerUpdateRequest.getSystemId())
                .eq(SysRoleUserPo::getRoleType, SysRelationEnum.SYSTEM_DEVELOPER.getType()));

        /**
         * 已不存在表Sys_User_Resource，以下代码无用
         */
/*        SysUserResourcePo sysUserResourceUpdater = new SysUserResourcePo();
        sysUserResourceMapper.update(sysUserResourceUpdater, Wrappers.<SysUserResourcePo>lambdaUpdate()
                .set(SysUserResourcePo::getDelFlag,
                        developerUpdateRequest.isDeleteFlag() ? new Date().getTime()/1000 : 0)
                .eq(SysUserResourcePo::getUserId, developerUpdateRequest.getUserId())
                .eq(SysUserResourcePo::getResourceId, developerUpdateRequest.getSystemId())
                .eq(SysUserResourcePo::getRelationType, SysRelationEnum.SYSTEM_DEVELOPER.getType()));*/

        return new DeveloperUpdateResposne();
    }

}
