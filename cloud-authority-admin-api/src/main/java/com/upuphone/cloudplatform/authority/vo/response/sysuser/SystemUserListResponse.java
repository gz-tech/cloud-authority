package com.upuphone.cloudplatform.authority.vo.response.sysuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Classname SystemUserListResponse
 * @Description
 * @Date 2022/3/29 3:58 下午
 * @Created by gz-d
 */
@ApiModel(value = "用户list")
@Getter
@Setter
public class SystemUserListResponse {
    @ApiModelProperty(value = "用户list", required = true)
    List<SystemUserVo> systemUserList;
}
