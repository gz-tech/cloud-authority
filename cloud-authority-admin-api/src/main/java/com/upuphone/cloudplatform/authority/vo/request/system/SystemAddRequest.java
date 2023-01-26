package com.upuphone.cloudplatform.authority.vo.request.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Classname SystemAddRequest
 * @Description
 * @Date 2022/3/22 9:09 下午
 * @Created by gz-d
 */
@ApiModel(value = "新增应用")
@Getter
@Setter
public class SystemAddRequest {
    @ApiModelProperty(value = "应用名称", required = true)
    private String name;

    @ApiModelProperty(value = "应用code", required = true)
    private String code;

    @ApiModelProperty(value = "owner id", required = true)
    private String ownerUserId;

    @ApiModelProperty(value = "描述", required = true)
    private String description;
}
