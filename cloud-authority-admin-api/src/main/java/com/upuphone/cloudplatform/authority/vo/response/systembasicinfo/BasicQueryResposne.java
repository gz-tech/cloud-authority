package com.upuphone.cloudplatform.authority.vo.response.systembasicinfo;

import com.upuphone.cloudplatform.authority.vo.response.system.SystemVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("获取项目基本信息response")
public class BasicQueryResposne {

    @ApiModelProperty(value = "系统详情", required = true)
    private SystemVo systemVo;

}
