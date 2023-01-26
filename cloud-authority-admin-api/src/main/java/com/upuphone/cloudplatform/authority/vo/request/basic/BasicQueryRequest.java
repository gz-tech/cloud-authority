package com.upuphone.cloudplatform.authority.vo.request.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("获取项目基本信息request")
public class BasicQueryRequest {

    @ApiModelProperty(value = "系统ID", required = true)
    @NotNull
    private String systemId;

}
