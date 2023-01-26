package com.upuphone.cloudplatform.authority.business.service.basic.util;

import com.upuphone.cloudplatform.authority.mybatis.entity.SysUserPo;
import com.upuphone.cloudplatform.authority.vo.response.DeveloperDetailVo;
import com.upuphone.cloudplatform.common.exception.ServiceException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;

public class SystemUserConvertUtil {

    public static DeveloperDetailVo convertFromPo(SysUserPo sysUserPo) {
        if (sysUserPo == null) {
            throw new ServiceException(CommonErrorCode.PARAM_ERROR);
        }
        DeveloperDetailVo sysUserVo = new DeveloperDetailVo();
        sysUserVo.setId(sysUserPo.getId() + "");
        sysUserVo.setUid(sysUserPo.getUid());
        sysUserVo.setName(sysUserPo.getName());
        sysUserVo.setMobile(sysUserPo.getMobile());
        sysUserVo.setEmail(sysUserPo.getEmail());
        return sysUserVo;
    }

}
