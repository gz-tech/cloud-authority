package com.upuphone.cloudplatform.authority.api;

import com.upuphone.cloudplatform.authority.vo.request.resources.ResourceAddRequest;
import com.upuphone.cloudplatform.authority.vo.request.resources.ResourceDeleteRequest;
import com.upuphone.cloudplatform.authority.vo.request.resources.ResourceEditRequest;
import com.upuphone.cloudplatform.authority.vo.request.resources.ResourceListRequest;
import com.upuphone.cloudplatform.authority.vo.response.resources.ResourceListResponse;
import com.upuphone.cloudplatform.authority.vo.response.resources.ResourceVo;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import io.swagger.annotations.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Classname ResourceManagementClient
 * @Description
 * @Date 2022/3/23 1:44 下午
 * @Created by gz-d
 */
@Api(tags = "资源管理 API")
@FeignClient(name = "cloud-authority", contextId = "resource-manage")
public interface BizResourceClient {
    @PostMapping("/resource-manage/add")
    @ResponseBody
    @ApiOperation(value = "新增资源")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse add(@RequestBody ResourceAddRequest request);

    @PostMapping("/resource-manage/list")
    @ResponseBody
    @ApiOperation(value = "资源列表")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<ResourceListResponse> list(@RequestBody @Valid ResourceListRequest request);

    @PostMapping("/resource-manage/edit")
    @ResponseBody
    @ApiOperation(value = "编辑资源")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse edit(@RequestBody @Valid ResourceEditRequest request);

    @DeleteMapping("/resource-manage/delete")
    @ResponseBody
    @ApiOperation(value = "删除资源")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse delete(@RequestBody ResourceDeleteRequest request);

    @GetMapping("/resource-manage/detail")
    @ResponseBody
    @ApiOperation(value = "资源详情")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<ResourceVo> detail(@RequestParam("id")@ApiParam(value = "资源id",required = true) String id);
}
