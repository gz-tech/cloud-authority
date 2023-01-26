package com.upuphone.cloudplatform.authority.vo.response.systembasicinfo;

import com.upuphone.cloudplatform.authority.vo.response.DeveloperDetailVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("获取项目基本信息response")
public class DeveloperQueryResposne {

    @ApiModelProperty(value = "管理员信息", required = true)
    private List<DeveloperDetailVo> developers;

}
