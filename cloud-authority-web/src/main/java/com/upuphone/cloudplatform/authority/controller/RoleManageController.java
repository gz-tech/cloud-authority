package com.upuphone.cloudplatform.authority.controller;

import com.upuphone.cloudplatform.authority.api.RoleManageClient;
import com.upuphone.cloudplatform.authority.business.service.role.BizRoleAddService;
import com.upuphone.cloudplatform.authority.business.service.role.BizRoleDeleteService;
import com.upuphone.cloudplatform.authority.business.service.role.BizRoleListService;
import com.upuphone.cloudplatform.authority.business.service.role.BizRoleModifyService;
import com.upuphone.cloudplatform.authority.common.annotations.Authorization;
import com.upuphone.cloudplatform.authority.vo.request.role.BizRoleAddRequest;
import com.upuphone.cloudplatform.authority.vo.request.role.BizRoleDeleteRequest;
import com.upuphone.cloudplatform.authority.vo.request.role.BizRoleListRequest;
import com.upuphone.cloudplatform.authority.vo.request.role.BizRoleModifyRequest;
import com.upuphone.cloudplatform.authority.vo.response.role.BizRoleListResponse;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class RoleManageController implements RoleManageClient {

    @Autowired
    private BizRoleListService bizRoleListService;
    @Autowired
    private BizRoleModifyService bizRoleModifyService;
    @Autowired
    private BizRoleAddService bizRoleAddService;
    @Autowired
    private BizRoleDeleteService bizRoleDeleteService;

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:role:manage:list")
    public CommonResponse<BizRoleListResponse> getList(@Valid BizRoleListRequest request) {
        return CommonResponse.success(bizRoleListService.process(request));
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:role:manage:modify")
    public CommonResponse<Void> modify(@Valid BizRoleModifyRequest request) {
        bizRoleModifyService.process(request);
        return CommonResponse.success();
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:role:manage:add")
    public CommonResponse<Void> add(@Valid BizRoleAddRequest request) {
        bizRoleAddService.process(request);
        return CommonResponse.success();
    }

    @Override
    @Authorization(resourceId = "#request.systemId", permissionCode = "biz:role:manage:delete")
    public CommonResponse<Void> delete(@Valid BizRoleDeleteRequest request) {
        bizRoleDeleteService.process(request);
        return CommonResponse.success();
    }
}
