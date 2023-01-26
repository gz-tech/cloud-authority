package com.upuphone.cloudplatform.authority.api;

import com.upuphone.cloudplatform.authority.vo.response.sysuser.SystemUserListResponse;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname SystemUserClient
 * @Description
 * @Date 2022/3/29 4:10 下午
 * @Created by gz-d
 */
@Api(tags = "系统用户管理 API")
@FeignClient(name = "cloud-authority", contextId = "sys-user")
public interface SystemUserClient {
    @GetMapping("/sys-user-manage/list")
    @ResponseBody
    @ApiOperation("查询系统用户列表")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<SystemUserListResponse> list();

    @GetMapping("/sys-user-manage/developer-candidate-list")
    @ResponseBody
    @ApiOperation("系统开发者候选人列表")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<SystemUserListResponse> developerCandidateList(@RequestParam("systemId") String systemId);
}
