package com.upuphone.cloudplatform.authority.vo.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Min.Jiang
 * @Date 2022/5/24 11:08
 * @Version 1.0
 */

@Getter
@Setter
@ApiModel("用户角色详情")
public class SystemRoleUserDetail {
    @ApiModelProperty(value = "ID", example = "1")
    private String id;
    @ApiModelProperty(value = "用户ID", required = true, example = "1")
    private String userId;
}
