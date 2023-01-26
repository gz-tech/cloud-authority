package com.upuphone.cloudplatform.authority.vo.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("用户列表response")
public class BizUserListResponse {

    @ApiModelProperty("用户列表")
    private List<UserDetailVo> list;

}
