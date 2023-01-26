package com.upuphone.cloudplatform.authority.business.repo.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.upuphone.cloudplatform.authority.business.repo.IBizRoleService;
import com.upuphone.cloudplatform.authority.business.repo.IBizRoleUserService;
import com.upuphone.cloudplatform.authority.errorcode.AuthErrorCode;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizRolePermissionPo;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizRolePo;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizRoleUserPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRoleMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRolePermissionMapper;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
public class BizRoleServiceImpl extends ServiceImpl<BizRoleMapper, BizRolePo> implements IBizRoleService {

    @Autowired
    private IBizRoleUserService bizRoleUserService;
    @Autowired
    private BizRolePermissionMapper bizRolePermissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 10)
    public boolean addRole(Long systemId, String code, String description) {
        BizRolePo poByCode = baseMapper.getByCodeAndSystemIdForUpdate(code, systemId);
        if (null != poByCode) {
            log.error("角色code重复, roleId=[{}]", poByCode.getId());
            throw new BusinessException(AuthErrorCode.BIZ_ROLE_CODE_DUPLICATED);
        }
        return save(new BizRolePo().setCode(code).setDescription(description).setSystemId(systemId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 10)
    public boolean modifyRole(Long roleId, Long systemId, String code, String description) {
        BizRolePo poById = baseMapper.getByIdAndSystemIdForUpdate(roleId, systemId);
        if (null == poById) {
            log.error("角色不存在, roleId=[{}], systemId=[{}]", roleId, systemId);
            throw new BusinessException(AuthErrorCode.ILLEGAL_STATE);
        }
        BizRolePo poByCode = baseMapper.getByCodeAndSystemIdForUpdate(code, systemId);
        if (null != poByCode && !Objects.equals(poByCode.getId(), roleId)) {
            log.error("角色code重复, roleId=[{}]", poByCode.getId());
            throw new BusinessException(AuthErrorCode.BIZ_ROLE_CODE_DUPLICATED);
        }
        poById.setCode(code);
        poById.setDescription(description);
        return updateById(poById);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 10)
    public boolean deleteRole(Long roleId, Long systemId) {
        BizRolePo poById = baseMapper.getByIdAndSystemIdForUpdate(roleId, systemId);
        if (null == poById) {
            log.error("角色不存在, roleId=[{}], systemId=[{}]", roleId, systemId);
            throw new BusinessException(AuthErrorCode.ILLEGAL_STATE);
        }
        // 删除关联关系 能删就删 不重要
        bizRoleUserService.remove(Wrappers.<BizRoleUserPo>lambdaQuery().eq(BizRoleUserPo::getRoleId, roleId));
        bizRolePermissionMapper.delete(Wrappers.<BizRolePermissionPo>lambdaQuery().eq(BizRolePermissionPo::getRoleId, roleId));
        return removeById(poById);
    }
}
