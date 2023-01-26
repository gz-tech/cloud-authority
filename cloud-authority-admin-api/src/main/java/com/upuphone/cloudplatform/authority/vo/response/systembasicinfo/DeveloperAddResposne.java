package com.upuphone.cloudplatform.authority.vo.response.systembasicinfo;

import com.upuphone.cloudplatform.authority.vo.response.DeveloperDetailVo;
import com.upuphone.cloudplatform.authority.vo.response.user.SystemRoleUserDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("添加系统管理员response")
public class DeveloperAddResposne {

    @ApiModelProperty(value = "管理员信息", required = true)
    private List<DeveloperDetailVo> developers;
}
