package com.upuphone.cloudplatform.authority.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizRolePermissionPo;
import com.upuphone.cloudplatform.authority.mybatis.po.bizpermission.AuthorizedRoleListPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * 角色授权关系表（外部） Mapper 接口
 * </p>
 *
 * @author zhumeng.han
 * @since 2022-03-28
 */
@Mapper
public interface BizRolePermissionMapper extends BaseMapper<BizRolePermissionPo> {
    List<AuthorizedRoleListPo> getAuthorizedList(@Param("systemId") Long systemId);

    void deleteByResourceId(@Param("resourceId") Long resourceId);
}
