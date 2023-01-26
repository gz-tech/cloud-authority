package com.upuphone.cloudplatform.authority.controller;

import com.upuphone.cloudplatform.authority.api.SystemBasicInfoManageClient;
import com.upuphone.cloudplatform.authority.business.service.basic.AddDeveloperService;
import com.upuphone.cloudplatform.authority.business.service.basic.GetBasicInfoService;
import com.upuphone.cloudplatform.authority.business.service.basic.GetDeveloperService;
import com.upuphone.cloudplatform.authority.business.service.basic.UpdateBasicInfoService;
import com.upuphone.cloudplatform.authority.business.service.basic.UpdateDeveloperService;
import com.upuphone.cloudplatform.authority.common.annotations.Authorization;
import com.upuphone.cloudplatform.authority.common.constants.SysRelationEnum;
import com.upuphone.cloudplatform.authority.vo.request.basic.BasicQueryRequest;
import com.upuphone.cloudplatform.authority.vo.request.basic.BasicUpdateRequest;
import com.upuphone.cloudplatform.authority.vo.request.basic.DeveloperAddRequest;
import com.upuphone.cloudplatform.authority.vo.request.basic.DeveloperQueryRequest;
import com.upuphone.cloudplatform.authority.vo.request.basic.DeveloperUpdateRequest;
import com.upuphone.cloudplatform.authority.vo.response.systembasicinfo.BasicQueryResposne;
import com.upuphone.cloudplatform.authority.vo.response.systembasicinfo.BasicUpdateResposne;
import com.upuphone.cloudplatform.authority.vo.response.systembasicinfo.DeveloperAddResposne;
import com.upuphone.cloudplatform.authority.vo.response.systembasicinfo.DeveloperQueryResposne;
import com.upuphone.cloudplatform.authority.vo.response.systembasicinfo.DeveloperUpdateResposne;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemBasicInfoManageController implements SystemBasicInfoManageClient {

    @Autowired
    private GetBasicInfoService getBasicInfoService;

    @Autowired
    private UpdateBasicInfoService updateBasicInfoService;

    @Autowired
    private GetDeveloperService getDeveloperService;

    @Autowired
    private AddDeveloperService addDeveloperService;

    @Autowired
    private UpdateDeveloperService updateDeveloperService;

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "sys:basic:query")
    public CommonResponse<BasicQueryResposne> getBasicInfo(BasicQueryRequest request) {
        return CommonResponse.success(getBasicInfoService.process(request));
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "sys:basic:update", relationTypes = SysRelationEnum.SYSTEM_OWNER)
    public CommonResponse<BasicUpdateResposne> updateBasicInfo(BasicUpdateRequest request) {
        return CommonResponse.success(updateBasicInfoService.process(request));
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "sys:basic:developer:query")
    public CommonResponse<DeveloperQueryResposne> getDeveloper(DeveloperQueryRequest request) {
        return CommonResponse.success(getDeveloperService.process(request));
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "sys:basic:developer:add")
    public CommonResponse<DeveloperAddResposne> addDeveloper(DeveloperAddRequest request) {
        return CommonResponse.success(addDeveloperService.process(request));
    }

    @Override
    @Authorization(systemId = "#request.systemId", permissionCode = "sys:basic:developer:update", relationTypes = SysRelationEnum.SYSTEM_OWNER)
    public CommonResponse<DeveloperUpdateResposne> updateDeveloper(DeveloperUpdateRequest request) {
        return CommonResponse.success(updateDeveloperService.process(request));
    }
}
