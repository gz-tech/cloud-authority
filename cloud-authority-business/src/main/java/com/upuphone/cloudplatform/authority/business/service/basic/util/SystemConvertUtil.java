package com.upuphone.cloudplatform.authority.business.service.basic.util;

import com.upuphone.cloudplatform.authority.mybatis.entity.SysSystemPo;
import com.upuphone.cloudplatform.authority.vo.response.system.SystemVo;
import com.upuphone.cloudplatform.common.exception.ServiceException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;

public class SystemConvertUtil {

    public static SystemVo convertFromPo(SysSystemPo sysSystemPo) {
        if (sysSystemPo == null) {
            throw new ServiceException(CommonErrorCode.PARAM_ERROR);
        }
        SystemVo systemVo = new SystemVo();
        systemVo.setId(sysSystemPo.getId() + "");
        systemVo.setCode(sysSystemPo.getCode());
        systemVo.setName(sysSystemPo.getName());
        systemVo.setOwnerUserId(sysSystemPo.getOwnerUserId());
        sysSystemPo.setOwnerName(sysSystemPo.getOwnerName());
        systemVo.setDescription(sysSystemPo.getDescription());
        return systemVo;
    }
}
