package com.upuphone.cloudplatform.authority.business.repo;

import com.baomidou.mybatisplus.extension.service.IService;
import com.upuphone.cloudplatform.authority.common.constants.SysPermissionTypeEnum;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysPermissionPo;

import java.util.List;

public interface ISysPermissionService extends IService<SysPermissionPo> {

    /**
     * 根据条件获取接口权限列表
     *
     * @param sysUserId 内部用户ID
     * @param systemId systemId
     * @param types     指定查询的接口权限类型 不传为全查
     * @return 接口权限PO列表
     */
    List<SysPermissionPo> getPermissions(String sysUserId, String systemId, SysPermissionTypeEnum... types);

}
