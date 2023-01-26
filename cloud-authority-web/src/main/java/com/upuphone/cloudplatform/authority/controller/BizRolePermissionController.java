package com.upuphone.cloudplatform.authority.controller;

import com.upuphone.cloudplatform.authority.api.BizRolePermissionClient;
import com.upuphone.cloudplatform.authority.business.service.bizpermission.PermissionListService;
import com.upuphone.cloudplatform.authority.business.service.bizauthorize.*;
import com.upuphone.cloudplatform.authority.common.annotations.Authorization;
import com.upuphone.cloudplatform.authority.vo.request.rolepermission.*;
import com.upuphone.cloudplatform.authority.vo.response.rolepermission.AuthorizedRoleResponse;
import com.upuphone.cloudplatform.authority.vo.response.rolepermission.PermissionListResponse;
import com.upuphone.cloudplatform.authority.vo.response.rolepermission.RolePermissionDetailResponse;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BizRolePermissionController implements BizRolePermissionClient {
    @Autowired
    private RolePermissionAddService rolePermissionAddService;

    @Autowired
    private PermissionListService permissionListService;

    @Autowired
    private RolePermissionEditService rolePermissionEditService;

    @Autowired
    private RolePermissionDeleteService rolePermissionDeleteService;

    @Autowired
    private RolePermissionDetailService rolePermissionDetailService;

    @Autowired
    private AuthorizedRoleListService authorizedRoleListService;

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:authorize:add")
    public CommonResponse add(@Valid RolePermissionAddRequest request) {
        rolePermissionAddService.process(request);
        return CommonResponse.success();
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:authorize:edit")
    public CommonResponse edit(@Valid RolePermissionEditRequest request) {
        rolePermissionEditService.process(request);
        return CommonResponse.success();
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:authorize:detail")
    public CommonResponse<RolePermissionDetailResponse> detail(@Valid RolePermissionDetailRequest request) {
        return CommonResponse.success(rolePermissionDetailService.process(request.getRoleId()));
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:authorize:list")
    public CommonResponse<AuthorizedRoleResponse> list(@Valid RolePermissionListRequest request) {
        return CommonResponse.success(authorizedRoleListService.process(request.getSystemId()));
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:authorize:delete")
    public CommonResponse delete(@Valid RolePermissionDeleteRequest request) {
        rolePermissionDeleteService.process(request.getRoleId());
        return CommonResponse.success();
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:permission:list")
    public CommonResponse<PermissionListResponse> getPermissionList(@Valid BizSystemPermissionListRequest request) {
        return CommonResponse.success(permissionListService.process(request.getSystemId()));
    }
}
