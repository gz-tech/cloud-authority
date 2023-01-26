package com.upuphone.cloudplatform.authority.business.service.system;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizResourcePo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizResourceMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRoleMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRoleUserMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysRoleUserMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysSystemMapper;
import com.upuphone.cloudplatform.common.component.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname SystemDeleteService
 * @Description
 * @Date 2022/3/25 4:47 下午
 * @Created by gz-d
 */
@Service
public class SystemDeleteService extends BaseService<String, Integer> {

    @Autowired
    private SysSystemMapper sysSystemMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private BizRoleMapper bizRoleMapper;

    @Autowired
    private BizRoleUserMapper bizRoleUserMapper;
    @Autowired
    private BizResourceMapper bizResourceMapper;
    @Override
    protected void validate(String systemId) {

    }

    /**
     * 当sys_system删除一个应用时，需要同时在sys_role_user,biz_role,biz_role_user里删除相应数据
     */

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 10)
    protected Integer processCore(String systemId) throws Exception {
        sysSystemMapper.deleteById(Long.parseLong(systemId));
        sysRoleUserMapper.deleteById(Long.parseLong(systemId));
        bizRoleMapper.deleteById(Long.parseLong(systemId));
        bizRoleUserMapper.deleteByRoleID(Long.parseLong(systemId));
        bizResourceMapper.delete(Wrappers.<BizResourcePo>lambdaQuery().eq(BizResourcePo::getSystemId, systemId));
        return 1;
    }
}
