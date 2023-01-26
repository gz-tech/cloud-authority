package com.upuphone.cloudplatform.authority.vo.request.resources;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("删除资源request")
public class ResourceDeleteRequest {
    @ApiModelProperty(value = "资源ID", required = true)
    @NotEmpty(message = "resourceId 不为空")
    private String resourceId;

    @ApiModelProperty(value = "系统ID", required = true)
    @NotEmpty(message = "system id不为空")
    private String systemId;
}
