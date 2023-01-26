package com.upuphone.cloudplatform.authority.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizPermissionPo;
import com.upuphone.cloudplatform.authority.mybatis.po.bizpermission.BizPermissionListPo;
import com.upuphone.cloudplatform.authority.mybatis.po.bizuser.BizUserPermissionDetailPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 授权关系表（外部） Mapper 接口
 * </p>
 *
 * @author zhumeng.han
 * @since 2022-03-28
 */
@Mapper
public interface BizPermissionMapper extends BaseMapper<BizPermissionPo> {

    /**
     * 根据UserId和SystemId查询用户权限详情
     *
     * @param userId   userId
     * @param systemId systemId
     * @return BizUserPermissionDetailPo列表
     */
    List<BizUserPermissionDetailPo> getBizUserPermissionDetailsByUserIdAndSystemId(@Param("userId") String userId,
                                                                                   @Param("systemId") Long systemId);

    List<BizPermissionListPo> getAllPermissionListBySystemId(@Param("systemId") Long systemId);

    List<BizPermissionListPo> getPermissionByRoleId(@Param("roleId") Long roleId);

    List<Long> getPermtResIdByRole(@Param("roleIds") Set<Long> roleId, @Param("actionType") Integer actionType);

}
