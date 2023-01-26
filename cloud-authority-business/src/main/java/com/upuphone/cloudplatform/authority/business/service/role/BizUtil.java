package com.upuphone.cloudplatform.authority.business.service.role;

import com.upuphone.cloudplatform.authority.mybatis.entity.BizRolePo;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizRoleUserPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRoleMapper;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRoleUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Min.Jiang
 * @Date 2022/5/27 13:21
 * @Version 1.0
 */
@Component
public class BizUtil {

    @Autowired
    private BizRoleMapper bizRoleMapper;

    @Autowired
    private BizRoleUserMapper bizRoleUserMapper;

    public Long addBizRole(String code, String description, Long systemId) {
        BizRolePo bizRolePo = new BizRolePo();
        bizRolePo.setCode(code);
        bizRolePo.setDescription(description);
        bizRolePo.setSystemId(systemId);
        bizRoleMapper.insert(bizRolePo);
        Long id = bizRolePo.getId();
        return id;
    }

    public void addBizRoleUser(String ownerUserId, Long roleId) {
        BizRoleUserPo bizRoleUserPo = new BizRoleUserPo();
        bizRoleUserPo.setUserId(ownerUserId);
        bizRoleUserPo.setRoleId(roleId);
        bizRoleUserMapper.insert(bizRoleUserPo);
    }

}
