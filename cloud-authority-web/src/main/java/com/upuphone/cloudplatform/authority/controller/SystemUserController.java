package com.upuphone.cloudplatform.authority.controller;

import com.upuphone.cloudplatform.authority.api.SystemUserClient;
import com.upuphone.cloudplatform.authority.business.service.system.SystemDeveloperListService;
import com.upuphone.cloudplatform.authority.business.service.system.SystemUserService;
import com.upuphone.cloudplatform.authority.common.annotations.Authorization;
import com.upuphone.cloudplatform.authority.vo.response.sysuser.SystemUserListResponse;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SystemUserController
 * @Description
 * @Date 2022/3/29 4:12 下午
 * @Created by gz-d
 */
@RestController
public class SystemUserController implements SystemUserClient {
    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SystemDeveloperListService systemDeveloperListService;
    @Override
    @Authorization(permissionCode = "sys:user:list")
    public CommonResponse<SystemUserListResponse> list() {
        return CommonResponse.success(systemUserService.process(null));
    }

    @Override
    public CommonResponse<SystemUserListResponse> developerCandidateList(String systemId) {
        return CommonResponse.success(systemDeveloperListService.process(systemId));
    }
}
