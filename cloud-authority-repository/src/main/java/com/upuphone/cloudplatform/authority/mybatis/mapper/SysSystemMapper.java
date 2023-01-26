package com.upuphone.cloudplatform.authority.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysSystemPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统表（内部） Mapper 接口
 * </p>
 *
 * @author zhumeng.han
 * @since 2022-03-22
 */
@Mapper
public interface SysSystemMapper extends BaseMapper<SysSystemPo> {
    List<SysSystemPo>selectByCodeAndName(@Param("code") String code,
                                         @Param("name") String name);

    SysSystemPo selectByquery(@Param("id") Long id);

    SysSystemPo selectOwnerIdById(@Param("id") String id);

    List<SysSystemPo>selectbasicQuery(@Param("systemId") String systemId);
    
}
