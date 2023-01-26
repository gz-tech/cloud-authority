package com.upuphone.cloudplatform.authority.business.service.bizauthorize;

import com.upuphone.cloudplatform.authority.common.utils.OrikaUtil;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizRolePermissionMapper;
import com.upuphone.cloudplatform.authority.mybatis.po.bizpermission.AuthorizedRoleListPo;
import com.upuphone.cloudplatform.authority.vo.response.rolepermission.AuthorizedRoleResponse;
import com.upuphone.cloudplatform.authority.vo.response.rolepermission.AuthorizedRoleVo;
import com.upuphone.cloudplatform.common.component.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizedRoleListService extends BaseService<String, AuthorizedRoleResponse> {

    @Autowired
    private BizRolePermissionMapper bizRolePermissionMapper;

    @Override
    protected void validate(String s) {

    }

    @Override
    protected AuthorizedRoleResponse processCore(String systemId) throws Exception {
        List<AuthorizedRoleListPo> authorizedRolePoList = bizRolePermissionMapper.getAuthorizedList(Long.parseLong(systemId));
        AuthorizedRoleResponse result = new AuthorizedRoleResponse();
        result.setAuthorizedRoleVoList(OrikaUtil.mapAsList(authorizedRolePoList, AuthorizedRoleVo.class));
        return result;
    }
}
