package com.upuphone.cloudplatform.authority.api.rbac;

import com.upuphone.cloudplatform.authority.api.vo.request.AllPermissionRequest;
import com.upuphone.cloudplatform.authority.api.vo.request.CheckAllowRequest;
import com.upuphone.cloudplatform.authority.api.vo.response.AllPermissionResponse;
import com.upuphone.cloudplatform.authority.api.vo.response.CheckAllowResponse;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @Classname SystemUserClient
 * @Description
 * @Date 2022/3/29 4:10 下午
 * @Created by gz-d
 */

@Api(tags = "[开放接口][非客户端]rbac权限查询")
@FeignClient(name = "cloud-authority")
public interface RbacAuthorityClient {

    @PostMapping("/open/rbac/is-allowed")
    @ResponseBody
    @ApiOperation("查询用户对资源的访问权限")
    CommonResponse<CheckAllowResponse> isAllowed(@RequestBody @Valid CheckAllowRequest request);

    @PostMapping("/open/rbac/all-permission")
    @ResponseBody
    @ApiOperation("获取用户对全部资源的访问权限")
    CommonResponse<AllPermissionResponse> allPermission(@RequestBody @Valid AllPermissionRequest request);

    @GetMapping("/greeting")
    @ApiOperation("hello")
    String greeting();


}
