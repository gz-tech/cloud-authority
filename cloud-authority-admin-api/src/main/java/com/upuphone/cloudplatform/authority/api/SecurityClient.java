package com.upuphone.cloudplatform.authority.api;

import com.upuphone.cloudplatform.authority.vo.request.auth.AccessTokenRequest;
import com.upuphone.cloudplatform.authority.vo.response.auth.AccessTokenVo;
import com.upuphone.cloudplatform.authority.vo.response.security.SysUserDetailVo;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import io.swagger.annotations.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "安全 API")
@FeignClient(name = "cloud-authority", contextId = "security")
public interface SecurityClient {


    @ApiOperation("登出")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    @PostMapping("/logout")
    @ResponseBody
    CommonResponse<Void> logout();

    @ApiOperation("获取用户详情")
    @GetMapping("/get-system-user-details")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    @ResponseBody
    CommonResponse<SysUserDetailVo> getUserDetails();

    @ApiOperation("打个招呼")
    @GetMapping("/hello/greeting")
    @ResponseBody
    String hello();
}
