package com.upuphone.cloudplatform.authority.api.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("检查是否允许response")
public class CheckAllowResponse {

    @ApiModelProperty(value = "是否 允许")
    private boolean allow;
}
