package com.upuphone.cloudplatform.authority.vo.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("用户搜索request")
public class BizUserListRequest {

    @ApiModelProperty(value = "ldap格式用户名", example = "zhongwang.cao")
    private String name;
    @ApiModelProperty(value = "手机号", example = "13045775542")
    private String mobile;
    @ApiModelProperty(value = "邮箱", example = "123@qq.com")
    private String email;
    @ApiModelProperty(value = "系统ID", required = true)
    @NotEmpty
    private String systemId;

}

