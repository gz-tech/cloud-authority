package com.upuphone.cloudplatform.authority.api;


import com.upuphone.cloudplatform.authority.vo.request.basic.*;
import com.upuphone.cloudplatform.authority.vo.response.systembasicinfo.*;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import io.swagger.annotations.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "项目基本信息 API")
@FeignClient(name = "basic-manage")
public interface SystemBasicInfoManageClient {

    @GetMapping("/basic-manage/query-basic-info")
    @ResponseBody
    @ApiOperation("获取项目基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "登录态accessToken", required = true)
    })
    CommonResponse<BasicQueryResposne> getBasicInfo(@Valid BasicQueryRequest request);

    @PutMapping("/basic-manage/update-basic-info")
    @ResponseBody
    @ApiOperation("修改项目基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "登录态accessToken", required = true)
    })
    CommonResponse<BasicUpdateResposne> updateBasicInfo(@RequestBody @Valid BasicUpdateRequest request);

    @GetMapping("/basic-manage/query-system-developer")
    @ResponseBody
    @ApiOperation("查询项目开发者")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "登录态accessToken", required = true)
    })
    CommonResponse<DeveloperQueryResposne> getDeveloper(@Valid DeveloperQueryRequest request);

    @PostMapping("/basic-manage/add-system-developer")
    @ResponseBody
    @ApiOperation("添加开发者状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "登录态accessToken", required = true)
    })
    CommonResponse<DeveloperAddResposne> addDeveloper(@RequestBody @Valid DeveloperAddRequest request);


    @PutMapping("/basic-manage/udpate-system-developer")
    @ResponseBody
    @ApiOperation("更新开发者状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "登录态accessToken", required = true)
    })
    CommonResponse<DeveloperUpdateResposne> updateDeveloper(@RequestBody @Valid DeveloperUpdateRequest request);

}
