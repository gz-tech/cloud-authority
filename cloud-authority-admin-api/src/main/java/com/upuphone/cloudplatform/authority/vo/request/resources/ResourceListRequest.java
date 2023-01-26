package com.upuphone.cloudplatform.authority.vo.request.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Classname ResourceListRequest
 * @Description
 * @Date 2022/3/23 2:04 下午
 * @Created by gz-d
 */
@ApiModel(value = "资源列表")
@Getter
@Setter
public class ResourceListRequest {
    @ApiModelProperty(value = "系统id", required = true)
    @NotBlank(message = "系统id不为空")
    private String systemId;

    @ApiModelProperty(value = "资源名称(code)", required = true)
    private String code;

    @ApiModelProperty(value = "资源描述", required = true)
    private String description;

    @ApiModelProperty(value = "资源类型0-菜单 1-接口",
            allowableValues = "0,1", example = "1",required = true)
    private Integer resType;

    @ApiModelProperty(value = "分页大小", required = true)
    @NotNull(message = "page size不为空")
    private Integer pageSize;

    @ApiModelProperty(value = "分页数", required = true)
    @NotNull(message = "页码不为空")
    private Integer pageNum;

}
