package com.upuphone.cloudplatform.authority.api;

import com.upuphone.cloudplatform.authority.vo.request.role.BizRoleAddRequest;
import com.upuphone.cloudplatform.authority.vo.request.role.BizRoleDeleteRequest;
import com.upuphone.cloudplatform.authority.vo.request.role.BizRoleListRequest;
import com.upuphone.cloudplatform.authority.vo.request.role.BizRoleModifyRequest;
import com.upuphone.cloudplatform.authority.vo.response.role.BizRoleListResponse;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "角色管理 API")
@FeignClient(name = "cloud-authority", contextId = "role-manage")
public interface RoleManageClient {

    @GetMapping("/biz-role-manage/list")
    @ResponseBody
    @ApiOperation("获取角色列表")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<BizRoleListResponse> getList(@Valid BizRoleListRequest request);

    @PostMapping("/biz-role-manage/modify")
    @ResponseBody
    @ApiOperation("修改角色")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<Void> modify(@RequestBody @Valid BizRoleModifyRequest request);

    @PostMapping("/biz-role-manage/add")
    @ResponseBody
    @ApiOperation("新增角色")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<Void> add(@RequestBody @Valid BizRoleAddRequest request);

    @DeleteMapping("/biz-role-manage/delete")
    @ResponseBody
    @ApiOperation("删除角色")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<Void> delete(@RequestBody @Valid BizRoleDeleteRequest request);

}
