package com.upuphone.cloudplatform.authority.controller;

import com.upuphone.cloudplatform.authority.api.UserManageClient;
import com.upuphone.cloudplatform.authority.business.service.user.BizUserAddService;
import com.upuphone.cloudplatform.authority.business.service.user.BizUserDetailService;
import com.upuphone.cloudplatform.authority.business.service.user.BizUserListService;
import com.upuphone.cloudplatform.authority.business.service.user.BizUserPermissionListService;
import com.upuphone.cloudplatform.authority.business.service.user.BizUserRoleAddService;
import com.upuphone.cloudplatform.authority.business.service.user.BizUserRoleDeleteService;
import com.upuphone.cloudplatform.authority.business.service.user.BizUserRoleListService;
import com.upuphone.cloudplatform.authority.common.annotations.Authorization;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserAddRequest;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserAddRoleRequest;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserDeleteRoleRequest;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserDetailRequest;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserListRequest;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserPermissionListRequest;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserRoleListRequest;
import com.upuphone.cloudplatform.authority.vo.response.user.BizUserListResponse;
import com.upuphone.cloudplatform.authority.vo.response.user.BizUserPermissionListResponse;
import com.upuphone.cloudplatform.authority.vo.response.user.BizUserRoleListResponse;
import com.upuphone.cloudplatform.authority.vo.response.user.UserDetailVo;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class UserManageController implements UserManageClient {

    @Autowired
    private BizUserListService bizUserListService;
    @Autowired
    private BizUserAddService bizUserAddService;
    @Autowired
    private BizUserDetailService bizUserDetailService;
    @Autowired
    private BizUserRoleListService bizUserRoleListService;
    @Autowired
    private BizUserRoleAddService bizUserRoleAddService;
    @Autowired
    private BizUserPermissionListService bizUserPermissionListService;
    @Autowired
    private BizUserRoleDeleteService bizUserRoleDeleteService;

    @Override
    @Authorization(resourceId = "#request.systemId", permissionCode = "biz:user:manage:list")
    public CommonResponse<BizUserListResponse> getList(@Valid BizUserListRequest request) {
        return CommonResponse.success(bizUserListService.process(request));
    }

    @Override
    @Authorization(resourceId = "#request.systemId", permissionCode = "biz:user:manage:add")
    public CommonResponse<Void> add(@Valid BizUserAddRequest request) {
        bizUserAddService.process(request);
        return CommonResponse.success();
    }

    @Override
    @Authorization(resourceId = "#request.systemId", permissionCode = "biz:user:manage:detail")
    public CommonResponse<UserDetailVo> getDetail(@Valid BizUserDetailRequest request) {
        return CommonResponse.success(bizUserDetailService.process(request));
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:user:manage:role:list")
    public CommonResponse<BizUserRoleListResponse> getRoleList(@Valid BizUserRoleListRequest request) {
        return CommonResponse.success(bizUserRoleListService.process(request));
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:user:manage:role:add")
    public CommonResponse<Void> addRole(@Valid BizUserAddRoleRequest request) {
        bizUserRoleAddService.process(request);
        return CommonResponse.success();
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:user:manage:permission:list")
    public CommonResponse<BizUserPermissionListResponse> getPermissionList(@Valid BizUserPermissionListRequest request) {
        return CommonResponse.success(bizUserPermissionListService.process(request));
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:user:manage:role:delete")
    public CommonResponse<Void> deleteRole(@Valid BizUserDeleteRoleRequest request) {
        bizUserRoleDeleteService.process(request);
        return CommonResponse.success();
    }
}
