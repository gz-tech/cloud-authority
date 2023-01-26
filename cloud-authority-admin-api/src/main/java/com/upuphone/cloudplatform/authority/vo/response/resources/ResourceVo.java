package com.upuphone.cloudplatform.authority.vo.response.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Classname ResourceVo
 * @Description
 * @Date 2022/3/23 4:37 下午
 * @Created by gz-d
 */
@ApiModel(value = "资源")
@Getter
@Setter
public class ResourceVo {
    @ApiModelProperty(value = "资源id", required = true)
    private String id;

    @ApiModelProperty(value = "资源名称(唯一)", required = true)
    private String code;

    @ApiModelProperty(value = "资源描述", required = true)
    private String description;

    @ApiModelProperty(value = "资源路径", required = true)
    private String path;

    @ApiModelProperty(value = "资源类型0-菜单 1-接口",
            allowableValues = "0,1", example = "1",required = true)
    private Integer resType;
}
