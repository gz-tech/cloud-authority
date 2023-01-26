package com.upuphone.cloudplatform.authority.vo.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("用户详情")
public class UserDetailVo {

    @ApiModelProperty(value = "用户ID", example = "1")
    private String id;
    @ApiModelProperty(value = "外部系统用户ID", required = true, example = "1")
    private String uid;
    @ApiModelProperty(value = "名字", required = true, example = "曹中旺")
    private String name;
    @ApiModelProperty(value = "手机号", example = "13045775542")
    private String mobile;
    @ApiModelProperty(value = "邮箱", example = "123@qq.com")
    private String email;

}
