package com.upuphone.cloudplatform.authority.vo.request.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("获取项目基本信息request")
public class DeveloperQueryRequest {

    @ApiModelProperty(value = "用戶id", required = true)
    private String operator;

    @ApiModelProperty(value = "系统id", required = true)
    private String systemId;

}
