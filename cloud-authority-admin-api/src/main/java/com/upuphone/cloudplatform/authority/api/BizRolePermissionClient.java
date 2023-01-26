package com.upuphone.cloudplatform.authority.api;

import com.upuphone.cloudplatform.authority.vo.request.rolepermission.*;
import com.upuphone.cloudplatform.authority.vo.response.rolepermission.AuthorizedRoleResponse;
import com.upuphone.cloudplatform.authority.vo.response.rolepermission.PermissionListResponse;
import com.upuphone.cloudplatform.authority.vo.response.rolepermission.RolePermissionDetailResponse;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import io.swagger.annotations.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Classname GrantManagementClient
 * @Description
 * @Date 2022/3/23 2:51 下午
 * @Created by gz-d
 */
@Api(tags = "授权管理 API")
@FeignClient(name = "cloud-authority", contextId = "role-resource-manage")
public interface BizRolePermissionClient {

    @PostMapping("/role-permission/add")
    @ResponseBody
    @ApiOperation("新增授权")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse add(@RequestBody @Valid RolePermissionAddRequest request);

    @PostMapping("/role-permission/edit")
    @ResponseBody
    @ApiOperation("编辑授权")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse edit(@RequestBody @Valid RolePermissionEditRequest request);

    @GetMapping ("/role-permission/detail")
    @ResponseBody
    @ApiOperation("查看授权详情")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<RolePermissionDetailResponse> detail(RolePermissionDetailRequest request);

    @GetMapping ("/role-permission/list")
    @ResponseBody
    @ApiOperation("已授权角色列表")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<AuthorizedRoleResponse> list(RolePermissionListRequest request);

    @DeleteMapping ("/role-permission/delete")
    @ApiOperation("取消授权")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse delete(@RequestBody RolePermissionDeleteRequest request);

    @GetMapping ("/permission/list")
    @ApiOperation("权限列表")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<PermissionListResponse> getPermissionList(BizSystemPermissionListRequest request);


}
