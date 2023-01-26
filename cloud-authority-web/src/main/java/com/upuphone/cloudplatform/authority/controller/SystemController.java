package com.upuphone.cloudplatform.authority.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.upuphone.cloudplatform.authority.api.SystemClient;
import com.upuphone.cloudplatform.authority.business.service.system.*;
import com.upuphone.cloudplatform.authority.common.annotations.Authorization;
import com.upuphone.cloudplatform.authority.vo.request.system.SystemAddRequest;
import com.upuphone.cloudplatform.authority.vo.request.system.SystemEditRequest;
import com.upuphone.cloudplatform.authority.vo.request.system.SystemListRequest;
import com.upuphone.cloudplatform.authority.vo.response.system.SystemVo;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Classname SystemManagementController
 * @Description
 * @Date 2022/3/23 5:48 下午
 * @Created by gz-d
 */
@RestController
public class SystemController implements SystemClient {
    @Autowired
    private SystemListService systemListService;

    @Autowired
    private SystemAddService systemAddService;

    @Autowired
    private SystemEditService systemEditService;

    @Autowired
    private SystemDeleteService systemDeleteService;

    @Autowired
    private SystemDetailService systemDetailService;

    @Override
    @Authorization(permissionCode = "sys:system:list")
    public CommonResponse<PageDTO<SystemVo>> list(@Valid SystemListRequest request) {
        return CommonResponse.success(systemListService.process(request));
    }

    @Override
    @Authorization(permissionCode = "sys:system:add")
    public CommonResponse add(@Valid SystemAddRequest request) {
        systemAddService.process(request);
        return CommonResponse.success();
    }

    @Override
    @Authorization(permissionCode = "sys:system:edit")
    public CommonResponse edit(@Valid SystemEditRequest request) {
        systemEditService.process(request);
        return CommonResponse.success();
    }

    @Override
    @Authorization(permissionCode = "sys:system:delete")
    public CommonResponse delete(String id) {
        systemDeleteService.process(id);
        return CommonResponse.success();
    }

    @Override
    @Authorization(permissionCode = "sys:system:detail")
    public CommonResponse<SystemVo> detail(String id) {
        return CommonResponse.success(systemDetailService.process(id));
    }
}
