package com.upuphone.cloudplatform.authority.business.service.bizresource;

import com.upuphone.cloudplatform.authority.business.service.bizpermission.Permission;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizResourceMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRolePermissionMapper;
import com.upuphone.cloudplatform.common.component.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname ResourceDeleteService
 * @Description
 * @Date 2022/3/25 3:03 下午
 * @Created by gz-d
 */
@Service
public class ResourceDeleteService extends BaseService<String, Integer> {
    @Autowired
    private BizResourceMapper bizResourceMapper;

    @Autowired
    private Permission permission;

    @Autowired
    private BizRolePermissionMapper bizRolePermissionMapper;

    @Override
    protected void validate(String s) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected Integer processCore(String resourceId) throws Exception {
        bizResourceMapper.deleteById(Long.parseLong(resourceId));
        permission.deleteByResourceId(resourceId);
        bizRolePermissionMapper.deleteByResourceId(Long.parseLong(resourceId));
        return 1;
    }
}
