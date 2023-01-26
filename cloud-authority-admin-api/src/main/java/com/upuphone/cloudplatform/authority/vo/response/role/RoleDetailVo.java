package com.upuphone.cloudplatform.authority.vo.response.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("角色详情VO")
public class RoleDetailVo {

    @ApiModelProperty("角色ID")
    private String id;

    @ApiModelProperty("角色code")
    private String code;

    @ApiModelProperty("角色描述")
    private String description;

    @ApiModelProperty(value = "创建时间", example = "2022-03-16 18:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

}
