package com.upuphone.cloudplatform.authority.business.service.bizauthorize;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizRolePermissionPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRolePermissionMapper;
import com.upuphone.cloudplatform.common.component.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname RolePermissionDeleteService
 * @Description
 * @Date 2022/3/30 7:10 下午
 * @Created by gz-d
 */
@Service
public class RolePermissionDeleteService extends BaseService<String, Integer> {
    @Autowired
    private BizRolePermissionMapper rolePermissionMapper;

    @Override
    protected void validate(String s) {

    }

    @Override
    protected Integer processCore(String roleId) throws Exception {
        rolePermissionMapper.delete(Wrappers.<BizRolePermissionPo>lambdaQuery().eq(BizRolePermissionPo::getRoleId, Long.parseLong(roleId)));
        return 1;
    }
}
