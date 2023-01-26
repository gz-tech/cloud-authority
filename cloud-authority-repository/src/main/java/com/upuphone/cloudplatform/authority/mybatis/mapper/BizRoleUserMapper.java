package com.upuphone.cloudplatform.authority.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizRoleUserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色表（业务） Mapper 接口
 * </p>
 *
 * @author zhumeng.han
 * @since 2022-03-22
 */
@Mapper
public interface BizRoleUserMapper extends BaseMapper<BizRoleUserPo> {

    /**
     * 根据biz_role_user.ID 和systemId 查询biz_role_user关系数量
     *
     * @param id       biz_role_user.ID
     * @param systemId systemId
     * @return biz_role_user记录数（正常为1）
     */
    Long getCountByIdAndSystemId(@Param("id") Long id, @Param("systemId") Long systemId);

    /**
     * 根据userId查询该用户roleId列表（带X锁）
     *
     * @param userId userId
     * @return 该用户roleId列表
     */
    List<Long> getRoleIdsByUserIdForUpdate(@Param("userId") String userId);

    List<Long> getRoleIdsByUserUid(@Param("uid") String userUid,@Param("systemId")Long systemId);

    List<String> getRoleCodesByUidAndSystemId(@Param("uid") String userUid, @Param("systemId")Long systemId);

    void deleteByRoleID(@Param("systemId") Long systemId);

    void updateByUserId(@Param("id") Long id, @Param("ownerId") String ownerId);

}
