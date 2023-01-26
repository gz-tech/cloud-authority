package com.upuphone.cloudplatform.authority.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysRoleUserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户系统角色关联表（内部） Mapper 接口
 * </p>
 *
 * @author zhumeng.han
 * @since 2022-03-25
 */
@Mapper
public interface SysRoleUserMapper extends BaseMapper<SysRoleUserPo> {
    List<SysRoleUserPo> selectDevelopersBySystemId(@Param("systemId") Long systemId);

    void updateByUserId(@Param("id")  Long id,
                                       @Param("ownerId") String ownerId);

    List<String> selectOwnerId(@Param("code") String code,
                               @Param("name") String name);

    List<String> selectOwnerIdList(@Param("systemId")  String systemId);

    List<SysRoleUserPo> selectDevelopersBySystemIdAndOperator(@Param("systemId") Long systemId,
                                                              @Param("operator") String operator);

}
