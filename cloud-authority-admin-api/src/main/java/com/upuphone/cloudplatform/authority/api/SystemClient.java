package com.upuphone.cloudplatform.authority.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.upuphone.cloudplatform.authority.vo.request.system.SystemAddRequest;
import com.upuphone.cloudplatform.authority.vo.request.system.SystemEditRequest;
import com.upuphone.cloudplatform.authority.vo.request.system.SystemListRequest;
import com.upuphone.cloudplatform.authority.vo.response.system.SystemVo;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import io.swagger.annotations.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Classname SystemManagementClient
 * @Description
 * @Date 2022/3/22 6:37 下午
 * @Created by gz-d
 */
@Api(tags = "应用管理API")
@FeignClient(name = "cloud-authority", contextId = "system-manage")
public interface SystemClient {

    @PostMapping("/system-manage/list")
    @ResponseBody
    @ApiOperation("应用列表")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<PageDTO<SystemVo>> list(@RequestBody @Valid SystemListRequest request);

    @PostMapping("/system-manage/add")
    @ResponseBody
    @ApiOperation("新增应用")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse add(@RequestBody @Valid SystemAddRequest request);

    @PostMapping("/system-manage/edit")
    @ResponseBody
    @ApiOperation("编辑应用")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse edit(@RequestBody @Valid SystemEditRequest request);

    @DeleteMapping("/system-manage/delete/{id}")
    @ResponseBody
    @ApiOperation("删除应用")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse delete(@PathVariable String id);

    @GetMapping("/system-manage/detail")
    @ResponseBody
    @ApiOperation("应用详情")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "header", name = "admin-access-token", value = "access_token", required = true)
    )
    CommonResponse<SystemVo> detail(@RequestParam("id")@ApiParam(value = "应用id",required = true) String id);

}
