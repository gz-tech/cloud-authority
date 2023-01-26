package com.upuphone.cloudplatform.authority.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizUserPo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表（外部） Mapper 接口
 * </p>
 *
 * @author zhumeng.han
 * @since 2022-03-28
 */
public interface BizUserMapper extends BaseMapper<BizUserPo> {

    /**
     * 根据userId和systemId查询业务userPO（带X锁）
     *
     * @param userId   userId
     * @param systemId systemId
     * @return BizUserPo
     */
    BizUserPo selectByIdAndSystemIdForUpdate(@Param("userId") Long userId, @Param("systemId") Long systemId);

}
