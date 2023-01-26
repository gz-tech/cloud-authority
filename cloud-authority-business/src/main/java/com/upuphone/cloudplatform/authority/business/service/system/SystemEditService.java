package com.upuphone.cloudplatform.authority.business.service.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysSystemPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRoleUserMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysRoleUserMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysSystemMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysUserResourceMapper;
import com.upuphone.cloudplatform.authority.vo.request.system.SystemEditRequest;
import com.upuphone.cloudplatform.common.component.BaseService;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @Classname SystemEditService
 * @Description
 * @Date 2022/3/25 4:47 下午
 * @Created by gz-d
 */
@Service
public class SystemEditService extends BaseService<SystemEditRequest, Integer> {
    @Autowired
    private SysSystemMapper sysSystemMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysUserResourceMapper sysUserResourceMapper;

    @Autowired
    private BizRoleUserMapper bizRoleUserMapper;

    @Autowired
    private SystemUtil systemUtil;

    @Override
    protected void validate(SystemEditRequest systemEditRequest) {

    }

    @Override
    protected Integer processCore(SystemEditRequest request) throws Exception {
        SysSystemPo sysSystemPo = sysSystemMapper.selectByquery(request.getId());
        if (null == sysSystemPo) {
            throw new BusinessException(CommonErrorCode.PARAM_ERROR, "未找到应用");
        }
        SysSystemPo updateSystem = getUpdateEntity(request);
        sysSystemMapper.updateById(updateSystem);
        sysRoleUserMapper.updateByUserId(request.getId(), request.getOwnerUserId());
        return 1;
    }

    private SysSystemPo getUpdateEntity(SystemEditRequest request) {
        SysSystemPo updatePo = new SysSystemPo();
        updatePo.setId(request.getId());
        updatePo.setDescription(request.getDescription());
        if (!StringUtils.isEmpty(request.getDescription())) {
            updatePo.setDescription(request.getDescription());
        }
        if (!StringUtils.isEmpty(request.getName())) {
            updatePo.setName(request.getName());
        }

        return updatePo;
    }
}
