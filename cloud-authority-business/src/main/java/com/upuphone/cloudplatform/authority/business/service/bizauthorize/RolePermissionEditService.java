package com.upuphone.cloudplatform.authority.business.service.bizauthorize;

import com.upuphone.cloudplatform.authority.vo.request.rolepermission.RolePermissionAddRequest;
import com.upuphone.cloudplatform.authority.vo.request.rolepermission.RolePermissionEditRequest;
import com.upuphone.cloudplatform.common.component.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname RolePermissionEditService
 * @Description
 * @Date 2022/3/30 6:00 下午
 * @Created by gz-d
 */
@Service
public class RolePermissionEditService extends BaseService<RolePermissionEditRequest, Integer> {
    @Autowired
    private RolePermissionDeleteService rolePermissionDeleteService;

    @Autowired
    private RolePermissionAddService rolePermissionAddService;

    @Override
    protected void validate(RolePermissionEditRequest rolePermissionEditRequest) {

    }

    @Override
    protected Integer processCore(RolePermissionEditRequest request) throws Exception {
        rolePermissionDeleteService.process(request.getAuthRoleId());
        List<String> roleList = new ArrayList<>();
        roleList.add(request.getAuthRoleId());
        RolePermissionAddRequest rolePermissionAddRequest = new RolePermissionAddRequest();
        rolePermissionAddRequest.setAuthRoleIdList(roleList);
        rolePermissionAddRequest.setAuthPermissionIdList(request.getAuthPermissionIdList());
        rolePermissionAddService.process(rolePermissionAddRequest);
        return 1;
    }
}
