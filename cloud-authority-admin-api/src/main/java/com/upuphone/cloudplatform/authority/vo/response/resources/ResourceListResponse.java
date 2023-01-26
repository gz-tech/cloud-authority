package com.upuphone.cloudplatform.authority.vo.response.resources;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Classname ResourceListResponse
 * @Description
 * @Date 2022/3/23 4:35 下午
 * @Created by gz-d
 */
@ApiModel(value = "资源列表")
@Getter
@Setter
public class ResourceListResponse {
    @ApiModelProperty(value = "分页数据", required = true)
    private PageDTO<ResourceVo> resourceList;
}
