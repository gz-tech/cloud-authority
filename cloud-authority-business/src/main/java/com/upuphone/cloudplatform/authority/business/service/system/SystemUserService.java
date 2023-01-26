package com.upuphone.cloudplatform.authority.business.service.system;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.upuphone.cloudplatform.authority.common.utils.OrikaUtil;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysUserPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysUserMapper;
import com.upuphone.cloudplatform.authority.vo.response.sysuser.SystemUserListResponse;
import com.upuphone.cloudplatform.authority.vo.response.sysuser.SystemUserVo;
import com.upuphone.cloudplatform.common.component.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname SystemUserService
 * @Description
 * @Date 2022/3/29 3:45 下午
 * @Created by gz-d
 */
@Service
public class SystemUserService extends BaseService<Void, SystemUserListResponse> {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    protected void validate(Void unused) {

    }

    @Override
    protected SystemUserListResponse processCore(Void unused) throws Exception {
        List<SysUserPo> queryResult = sysUserMapper.selectList(Wrappers.<SysUserPo>lambdaQuery());
        SystemUserListResponse response = new SystemUserListResponse();
        response.setSystemUserList(OrikaUtil.mapAsList(queryResult, SystemUserVo.class));
        return response;
    }
}
