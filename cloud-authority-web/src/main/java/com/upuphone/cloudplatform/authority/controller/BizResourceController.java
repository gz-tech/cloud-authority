package com.upuphone.cloudplatform.authority.controller;

import com.upuphone.cloudplatform.authority.api.BizResourceClient;
import com.upuphone.cloudplatform.authority.business.service.bizresource.*;
import com.upuphone.cloudplatform.authority.common.annotations.Authorization;
import com.upuphone.cloudplatform.authority.vo.request.resources.ResourceAddRequest;
import com.upuphone.cloudplatform.authority.vo.request.resources.ResourceDeleteRequest;
import com.upuphone.cloudplatform.authority.vo.request.resources.ResourceEditRequest;
import com.upuphone.cloudplatform.authority.vo.request.resources.ResourceListRequest;
import com.upuphone.cloudplatform.authority.vo.response.resources.ResourceListResponse;
import com.upuphone.cloudplatform.authority.vo.response.resources.ResourceVo;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Classname ResourceManagementController
 * @Description
 * @Date 2022/3/23 5:49 下午
 * @Created by gz-d
 */
@RestController
public class BizResourceController implements BizResourceClient {
    @Autowired
    private ResourceAddService resourceAddService;

    @Autowired
    private ResourceDeleteService resourceDeleteService;

    @Autowired
    private ResourceEditService resourceEditService;

    @Autowired
    private ResourceDetailService resourceDetailService;

    @Autowired
    private ResourceListService resourceListService;

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:resource:add")
    public CommonResponse add(@Valid ResourceAddRequest request) {
        resourceAddService.process(request);
        return CommonResponse.success();
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:resource:list")
    public CommonResponse<ResourceListResponse> list(@Valid ResourceListRequest request) {
        return CommonResponse.success(resourceListService.process(request));
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:resource:edit")
    public CommonResponse edit(@Valid ResourceEditRequest request) {
        resourceEditService.process(request);
        return CommonResponse.success();
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "biz:resource:delete")
    public CommonResponse delete(@Valid ResourceDeleteRequest request) {
        resourceDeleteService.process(request.getResourceId());
        return CommonResponse.success();
    }

    @Override
    @Authorization(permissionCode = "biz:resource:detail")
    public CommonResponse<ResourceVo> detail(String id) {
        return CommonResponse.success(resourceDetailService.process(id));
    }
}
