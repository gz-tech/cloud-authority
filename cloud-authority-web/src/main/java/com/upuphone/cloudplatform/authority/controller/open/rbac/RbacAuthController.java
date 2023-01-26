package com.upuphone.cloudplatform.authority.controller.open.rbac;

import com.upuphone.cloudplatform.authority.api.rbac.RbacAuthorityClient;
import com.upuphone.cloudplatform.authority.api.vo.request.AllPermissionRequest;
import com.upuphone.cloudplatform.authority.api.vo.request.CheckAllowRequest;
import com.upuphone.cloudplatform.authority.api.vo.response.AllPermissionResponse;
import com.upuphone.cloudplatform.authority.api.vo.response.CheckAllowResponse;
import com.upuphone.cloudplatform.authority.business.service.open.rbac.AllPermissionService;
import com.upuphone.cloudplatform.authority.business.service.open.rbac.CheckAllowService;
import com.upuphone.cloudplatform.authority.security.open.model.CheckSecret;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import com.upuphone.cloudplatform.internal.admin.starter.anno.DisableSso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class RbacAuthController implements RbacAuthorityClient {

    @Autowired
    private CheckAllowService checkAllowService;

    @Autowired
    private AllPermissionService allPermissionService;


    @Override
    @CheckSecret
    @DisableSso
    public CommonResponse<CheckAllowResponse> isAllowed(@Valid CheckAllowRequest request) {

        return CommonResponse.success(checkAllowService.process(request));
    }

    @Override
    // @CheckSecret
    public CommonResponse<AllPermissionResponse> allPermission(AllPermissionRequest request) {

        return CommonResponse.success(allPermissionService.process(request));
    }


    @Override
    public String greeting() {
        return "Hello";
    }
}
