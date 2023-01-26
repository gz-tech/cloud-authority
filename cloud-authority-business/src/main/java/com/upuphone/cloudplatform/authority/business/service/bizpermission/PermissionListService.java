package com.upuphone.cloudplatform.authority.business.service.bizpermission;

import com.upuphone.cloudplatform.authority.mybatis.mapper.BizPermissionMapper;
import com.upuphone.cloudplatform.authority.mybatis.po.bizpermission.BizPermissionListPo;
import com.upuphone.cloudplatform.authority.vo.response.rolepermission.PermissionListResponse;
import com.upuphone.cloudplatform.common.component.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionListService extends BaseService<String, PermissionListResponse> {
    @Autowired
    private BizPermissionMapper permissionMapper;

    @Autowired
    private Permission permission;

    @Override
    protected void validate(String systemId) {

    }

    @Override
    protected PermissionListResponse processCore(String systemId) throws Exception {
        List<BizPermissionListPo> permissionPoList = permissionMapper.getAllPermissionListBySystemId(Long.parseLong(systemId));
        PermissionListResponse result = new PermissionListResponse();
        result.setPermissionList(permission.permissionPo2Vo(permissionPoList));
        return result;
    }
}
