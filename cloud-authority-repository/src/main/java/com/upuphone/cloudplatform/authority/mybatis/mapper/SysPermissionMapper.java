package com.upuphone.cloudplatform.authority.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysPermissionPo;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色接口权限表（内部） Mapper 接口
 * </p>
 *
 * @author zhumeng.han
 * @since 2022-03-25
 */
public interface SysPermissionMapper extends BaseMapper<SysPermissionPo> {

    /**
     * 根据userId和权限类型查询权限列表
     *
     * @param userId userId
     * @param systemId systemId
     * @param types  权限类型 不传为全查
     * @return SysPermissionPo列表
     */
    List<SysPermissionPo> getListByUserIdAndTypes(@Param("userId") String userId,  @Param("systemId") String systemId,
            @Param("types") Collection<Integer> types);

}
