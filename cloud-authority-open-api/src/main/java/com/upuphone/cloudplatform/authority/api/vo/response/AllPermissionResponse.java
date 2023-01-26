package com.upuphone.cloudplatform.authority.api.vo.response;

import com.upuphone.cloudplatform.authority.api.vo.ResourceVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("检查是否允许response")
public class AllPermissionResponse {

    @ApiModelProperty(value = "资源")
    private List<ResourceVo> resources;

    @ApiModelProperty(value = "角色")
    private List<String> roles;
}
