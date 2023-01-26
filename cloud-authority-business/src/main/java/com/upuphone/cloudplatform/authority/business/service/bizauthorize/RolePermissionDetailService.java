package com.upuphone.cloudplatform.authority.business.service.bizauthorize;

import com.upuphone.cloudplatform.authority.business.service.bizpermission.Permission;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizPermissionMapper;
import com.upuphone.cloudplatform.authority.mybatis.po.bizpermission.BizPermissionListPo;
import com.upuphone.cloudplatform.authority.vo.response.rolepermission.RolePermissionDetailResponse;
import com.upuphone.cloudplatform.common.component.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionDetailService extends BaseService<String, RolePermissionDetailResponse> {
    @Autowired
    private BizPermissionMapper bizPermissionMapper;

    @Autowired
    private Permission permission;

    @Override
    protected void validate(String s) {

    }

    @Override
    protected RolePermissionDetailResponse processCore(String s) throws Exception {
        List<BizPermissionListPo> permissionPoList = bizPermissionMapper.getPermissionByRoleId(Long.parseLong(s));
        RolePermissionDetailResponse result = new RolePermissionDetailResponse();
        result.setPermissionList(permission.permissionPo2DetailVo(permissionPoList));
        return result;
    }
}
