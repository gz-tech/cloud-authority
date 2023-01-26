package com.upuphone.cloudplatform.authority.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysUserResourcePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户资源表（内部） Mapper 接口
 * </p>
 *
 * @author zhumeng.han
 * @since 2022-03-25
 */
@Mapper
public interface SysUserResourceMapper extends BaseMapper<SysUserResourcePo> {

    int insertBatch(@Param("userResources") List<SysUserResourcePo> userResources);

}
