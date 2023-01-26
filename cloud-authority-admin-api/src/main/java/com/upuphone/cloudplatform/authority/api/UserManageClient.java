package com.upuphone.cloudplatform.authority.api;

import com.upuphone.cloudplatform.authority.vo.request.user.BizUserAddRequest;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserAddRoleRequest;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserDeleteRoleRequest;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserDetailRequest;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserListRequest;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserPermissionListRequest;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserRoleListRequest;
import com.upuphone.cloudplatform.authority.vo.response.user.BizUserListResponse;
import com.upuphone.cloudplatform.authority.vo.response.user.BizUserPermissionListResponse;
import com.upuphone.cloudplatform.authority.vo.response.user.BizUserRoleListResponse;
import com.upuphone.cloudplatform.authority.vo.response.user.UserDetailVo;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Api(tags = "外部用户管理 API")
@FeignClient(name = "cloud-authority", contextId = "biz-user-manage")
public interface UserManageClient {

    @GetMapping("/biz-user-manage/list")
    @ResponseBody
    @ApiOperation("查询用户列表")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<BizUserListResponse> getList(@Valid BizUserListRequest request);

    @PostMapping("/biz-user-manage/add")
    @ResponseBody
    @ApiOperation("新增用户")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<Void> add(@RequestBody @Valid BizUserAddRequest request);

    @GetMapping("/biz-user-manage/detail")
    @ResponseBody
    @ApiOperation("根据用户ID获取用户详情")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<UserDetailVo> getDetail(@Valid BizUserDetailRequest request);

    @GetMapping("/biz-user-manage/role/list")
    @ResponseBody
    @ApiOperation("根据用户ID和系统ID获取用户角色列表")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<BizUserRoleListResponse> getRoleList(@Valid BizUserRoleListRequest request);

    @PostMapping("/biz-user-manage/role/add")
    @ResponseBody
    @ApiOperation("新增用户角色")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<Void> addRole(@RequestBody @Valid BizUserAddRoleRequest request);

    @GetMapping("/biz-user-manage/permission/list")
    @ResponseBody
    @ApiOperation("根据用户ID和系统ID获取用户权限列表")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<BizUserPermissionListResponse> getPermissionList(@Valid BizUserPermissionListRequest request);

    @DeleteMapping("/biz-user-manage/role/delete")
    @ResponseBody
    @ApiOperation("根据用户ID和角色ID删除角色")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<Void> deleteRole(@RequestBody @Valid BizUserDeleteRoleRequest request);
}
