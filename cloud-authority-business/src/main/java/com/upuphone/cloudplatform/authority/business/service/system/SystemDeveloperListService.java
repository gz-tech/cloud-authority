package com.upuphone.cloudplatform.authority.business.service.system;

import com.upuphone.cloudplatform.authority.common.constants.SysRelationEnum;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysUserPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysUserMapper;
import com.upuphone.cloudplatform.authority.vo.response.sysuser.SystemUserListResponse;
import com.upuphone.cloudplatform.authority.vo.response.sysuser.SystemUserVo;
import com.upuphone.cloudplatform.common.component.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SystemDeveloperListService extends BaseService<String, SystemUserListResponse> {
    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    protected void validate(String systemId) {

    }

    @Override
    protected SystemUserListResponse processCore(String systemId) throws Exception {
        SystemUserListResponse allSystemUser = systemUserService.process(null);
        List<SysUserPo> boundedUserList = sysUserMapper.selectDevelopersBySystemId(Long.parseLong(systemId),null,null,
                SysRelationEnum.SYSTEM_DEVELOPER.getType());

        Set<String> boundedUserIdList = boundedUserList.stream().map(SysUserPo::getId).collect(Collectors.toSet());

        Iterator<SystemUserVo> it = allSystemUser.getSystemUserList().iterator();
        while(it.hasNext()) {
            if (boundedUserIdList.contains(Long.parseLong(it.next().getId()))) {
                it.remove();
            }
        }
        return allSystemUser;
    }
}
