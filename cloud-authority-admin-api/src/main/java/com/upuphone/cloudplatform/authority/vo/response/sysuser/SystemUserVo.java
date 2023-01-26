package com.upuphone.cloudplatform.authority.vo.response.sysuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Classname SystemUserVo
 * @Description
 * @Date 2022/3/29 3:56 下午
 * @Created by gz-d
 */
@ApiModel(value = "用户")
@Getter
@Setter
public class SystemUserVo {
    @ApiModelProperty(value = "用户ID", example = "1")
    private String id;
    @ApiModelProperty(value = "ldap格式用户名", required = true, example = "zhongwang.cao")
    private String uid;
    @ApiModelProperty(value = "名字", required = true, example = "曹中旺")
    private String name;
    @ApiModelProperty(value = "手机号", example = "13045775542")
    private String mobile;
    @ApiModelProperty(value = "邮箱", example = "123@qq.com")
    private String email;
}
