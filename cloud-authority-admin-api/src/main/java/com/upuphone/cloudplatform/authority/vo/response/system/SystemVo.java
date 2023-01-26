package com.upuphone.cloudplatform.authority.vo.response.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Classname SystemVo
 * @Description
 * @Date 2022/3/23 4:45 下午
 * @Created by gz-d
 */
@ApiModel(value = "应用")
@Getter
@Setter
public class SystemVo {
    @ApiModelProperty(value = "应用主键id")
    private String id;

    @ApiModelProperty(value = "应用code")
    private String code;

    @ApiModelProperty(value = "应用名称")
    private String name;

    @ApiModelProperty(value = "应用描述")
    private String description;

    @ApiModelProperty(value = "应用owner id")
    private String ownerUserId;

    @ApiModelProperty(value = "owner name", required = true)
    private String ownerName;
}
