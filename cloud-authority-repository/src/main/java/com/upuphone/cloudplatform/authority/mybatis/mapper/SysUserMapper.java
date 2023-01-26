package com.upuphone.cloudplatform.authority.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysUserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hanzhumeng
 * @since 2022-03-01
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserPo> {

    List<SysUserPo> selectDevelopersBySystemId(@Param("resourceId") Long resourceId,
                                               @Param("email") String email,
                                               @Param("name") String name,
                                                      @Param("relationType") Integer relationType);

}
