package com.upuphone.cloudplatform.authority.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizRolePo;
import com.upuphone.cloudplatform.authority.mybatis.po.bizuser.BizUserRoleDetailPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表（业务） Mapper 接口
 * </p>
 *
 * @author zhumeng.han
 * @since 2022-03-22
 */
@Mapper
public interface BizRoleMapper extends BaseMapper<BizRolePo> {

    /**
     * 根据UserId和SystemId查询用户角色详情列表
     *
     * @param userId   userId
     * @param systemId systemId
     * @return BizUserRoleDetailPo列表
     */
    List<BizUserRoleDetailPo> getBizUserRoleDetailsByUserIdAndSystemId(@Param("userId") String userId, @Param("systemId") Long systemId);

    /**
     * 根据角色code和systemId查询角色PO（带X锁）
     *
     * @param code     code
     * @param systemId systemId
     * @return BizRolePo
     */
    BizRolePo getByCodeAndSystemIdForUpdate(@Param("code") String code, @Param("systemId") Long systemId);

    /**
     * 根据roleId和systemId查询角色PO（带X锁）
     *
     * @param roleId   roleId
     * @param systemId systemId
     * @return BizRolePo
     */
    BizRolePo getByIdAndSystemIdForUpdate(@Param("roleId") Long roleId, @Param("systemId") Long systemId);

    /**
     * 根据角色ID列表和systemId查询角色PO列表（带X锁）
     *
     * @param ids      角色ID列表
     * @param systemId systemId
     * @return BizRolePo列表
     */
    List<BizRolePo> getByIdsAndSystemIdForUpdate(@Param("ids") Set<Long> ids, @Param("systemId") Long systemId);

}
