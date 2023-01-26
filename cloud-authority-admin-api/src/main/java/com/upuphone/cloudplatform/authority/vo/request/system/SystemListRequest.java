package com.upuphone.cloudplatform.authority.vo.request.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @Classname SystemListRequest
 * @Description
 * @Date 2022/3/23 2:27 下午
 * @Created by gz-d
 */
@ApiModel(value = "应用查询")
@Getter
@Setter
public class SystemListRequest {
    @ApiModelProperty(value = "应用名称", required = true)
    private String name;

    @ApiModelProperty(value = "应用code", required = true)
    private String code;

    @ApiModelProperty(value = "分页大小", required = true)
    @NotNull(message = "page size不为空")
    private Integer pageSize;

    @ApiModelProperty(value = "分页数", required = true)
    @NotNull(message = "页码不为空")
    private Integer pageNum;
}
