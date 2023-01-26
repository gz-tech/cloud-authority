package com.upuphone.cloudplatform.authority.vo.request.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Classname ResourceAddRequest
 * @Description
 * @Date 2022/3/23 1:54 下午
 * @Created by gz-d
 */
@ApiModel(value = "资源新增")
@Getter
@Setter
public class ResourceAddRequest {
    @ApiModelProperty(value = "资源名称(唯一)", required = true)
    @NotBlank(message = "资源唯一码不为空")
    private String code;

    @ApiModelProperty(value = "资源描述", required = true)
    private String description;

    @ApiModelProperty(value = "资源路径", required = true)
    private String path;

    @ApiModelProperty(value = "资源类型0-菜单 1-接口",
            allowableValues = "0,1", example = "1",required = true)
    @NotNull(message = "资源类型不为空")
    private Integer resType;

    @ApiModelProperty(value = "系统id",required = true)
    @NotBlank(message = "系统id不为空")
    private String systemId;
}
