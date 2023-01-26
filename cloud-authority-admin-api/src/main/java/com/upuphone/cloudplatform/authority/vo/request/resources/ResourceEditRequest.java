package com.upuphone.cloudplatform.authority.vo.request.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @Classname ResourceEditRequest
 * @Description
 * @Date 2022/3/23 2:07 下午
 * @Created by gz-d
 */
@ApiModel(value = "资源编辑")
@Getter
@Setter
public class ResourceEditRequest {
    @ApiModelProperty(value = "系统id", required = true)
    @NotBlank(message = "系统id不为空")
    private String systemId;

    @ApiModelProperty(value = "资源id", required = true)
    private Long id;

    @ApiModelProperty(value = "资源描述", required = true)
    private String description;

    @ApiModelProperty(value = "资源路径", required = true)
    private String path;

    @ApiModelProperty(value = "资源类型0-菜单 1-接口",
            allowableValues = "0,1", example = "1",required = true)
    private Integer resType;
}
