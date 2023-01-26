package com.upuphone.cloudplatform.authority.vo.request.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @Classname SystemEditRequest
 * @Description
 * @Date 2022/3/22 9:35 下午
 * @Created by gz-d
 */
@ApiModel(value = "编辑应用")
@Getter
@Setter
public class SystemEditRequest {
    @ApiModelProperty(value = "应用主键id", required = true)
    @NotNull(message = "id不为空")
    private Long id;

    @ApiModelProperty(value = "应用名称", required = true)
    private String name;

    @ApiModelProperty(value = "owner id", required = true)
    private String ownerUserId;

    @ApiModelProperty(value = "描述", required = true)
    private String description;
}
