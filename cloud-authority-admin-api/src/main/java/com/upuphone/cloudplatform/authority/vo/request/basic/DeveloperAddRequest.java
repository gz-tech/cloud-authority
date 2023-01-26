package com.upuphone.cloudplatform.authority.vo.request.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@ApiModel("添加系统管理员request")
public class DeveloperAddRequest {

    @ApiModelProperty(value = "系统Id", required = true)
    @NotBlank
    private String systemId;

    @ApiModelProperty(value = "管理员信息id", required = true)
    @NotEmpty
    private List<String> developerIds;


}
