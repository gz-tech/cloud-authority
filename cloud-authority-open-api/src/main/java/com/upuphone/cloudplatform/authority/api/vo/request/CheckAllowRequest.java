package com.upuphone.cloudplatform.authority.api.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("检查是否允许request")
public class CheckAllowRequest extends BaseRequest {

    @ApiModelProperty(value = "用户Id", required = true)
    @NotBlank(message = "用户id不为空")
    private String userId;

    @ApiModelProperty(value = "资源", required = false)
    @NotNull
    private ResourceRequest resourceRequest;

    @ApiModelProperty(value = "操作 类型：QUERY，默认:QUERY")
    private String action;

    @Getter
    @Setter
    public static final class ResourceRequest {
        @ApiModelProperty(value = "资源类型：API,MENU", required = true)
        private String resourceType;//api,menu

        @ApiModelProperty(value = "资源标识 resourceType为MENU")
        private String code;

        @ApiModelProperty(value = "资源值 resourceType为API")
        private String resourceValue;
    }

}
