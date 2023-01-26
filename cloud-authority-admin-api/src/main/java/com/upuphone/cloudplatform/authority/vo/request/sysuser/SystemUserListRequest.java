package com.upuphone.cloudplatform.authority.vo.request.sysuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Classname SystemUserListRequest
 * @Description
 * @Date 2022/3/29 3:52 下午
 * @Created by gz-d
 */
@ApiModel(value = "用户")
@Getter
@Setter
public class SystemUserListRequest {
    @ApiModelProperty(value = "姓名", required = true)
    private String name;
}
