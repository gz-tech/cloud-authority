package com.upuphone.cloudplatform.authority.vo.request.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("添加系统管理员request")
public class DeveloperUpdateRequest {

    @ApiModelProperty(value = "系统Id", required = true)
    @NotBlank
    private String systemId;

    @ApiModelProperty(value = "userId", required = true, example = "")
    @NotBlank
    private String userId;

    @ApiModelProperty(value = "是否删除", example = "1删除 0不删除")
    @NotNull
    private boolean deleteFlag;



}
