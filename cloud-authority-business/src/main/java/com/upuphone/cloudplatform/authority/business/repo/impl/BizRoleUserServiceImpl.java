package com.upuphone.cloudplatform.authority.business.repo.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.upuphone.cloudplatform.authority.business.repo.IBizRoleUserService;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizRolePo;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizRoleUserPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRoleMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRoleUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BizRoleUserServiceImpl extends ServiceImpl<BizRoleUserMapper, BizRoleUserPo> implements IBizRoleUserService {

    @Autowired
    private BizRoleMapper bizRoleMapper;

    @Override
    public Set<Long> getRoleIdsByUserId(String userId) {
        List<Long> roleIds = listObjs(Wrappers.<BizRoleUserPo>lambdaQuery()
                .select(BizRoleUserPo::getRoleId)
                .eq(BizRoleUserPo::getUserId, userId), roleId -> (Long) roleId);
        return new HashSet<>(roleIds);
    }


    @Override
    public void checkValidBizRoleUserAssociation(String roleUserId, Long systemId) {
        /**
         * biz_role和biz_user 关系已建立在biz_role_user,无需判断
         *
        if (baseMapper.getCountByIdAndSystemId(roleUserId, systemId) != 1) {
            throw new BusinessException(AuthErrorCode.ILLEGAL_STATE);
        }
         */
    }

    @Override
    public Set<Long> getRoleIdsByUserIdForUpdate(String userId) {
        return new HashSet<>(baseMapper.getRoleIdsByUserIdForUpdate(userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 10)
    public boolean addUserRole(Set<Long> roleIds, String userId, Long systemId) {
        /**
         * 已不需要判断userPO
        if (null == bizUserMapper.selectByIdAndSystemIdForUpdate(userId, systemId)) {
            throw new BusinessException(AuthErrorCode.ACCESS_DENIED);
        }*/
        List<BizRolePo> validRolePos = bizRoleMapper.getByIdsAndSystemIdForUpdate(roleIds, systemId);
        if (CollectionUtils.isEmpty(validRolePos)) {
            return true;
        }
        // 合法的角色ID
        Set<Long> validRoleIds = validRolePos.stream().map(BizRolePo::getId).collect(Collectors.toSet());
        // 用户已有的角色ID
        Set<Long> originRoleIds = getRoleIdsByUserIdForUpdate(userId);
        // 只插入没有的角色ID
        List<BizRoleUserPo> poListForInsert = SetUtils.difference(validRoleIds, originRoleIds).stream()
                .map(o -> new BizRoleUserPo().setRoleId(o).setUserId(userId)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(poListForInsert)) {
            return true;
        }
        return saveBatch(poListForInsert);
    }
}
