package com.upuphone.cloudplatform.authority.business.service.open.rbac.util;

import com.upuphone.cloudplatform.authority.business.service.open.rbac.model.SystemInfoVo;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysSystemPo;
import com.upuphone.cloudplatform.common.context.Context;
import com.upuphone.cloudplatform.common.exception.ServiceException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;

public class ContextUtil {

    private static final String SYSTEM_INFO = "SYSTEM_INFO";

    public static SystemInfoVo getSystemInfoVo() {
        return (SystemInfoVo) Context.get(SYSTEM_INFO);
    }

    public static void setSystemInfoToContext(SysSystemPo sysSystemPo) {
        if (sysSystemPo == null) {
            throw new ServiceException(CommonErrorCode.PARAM_ERROR);
        }

        SystemInfoVo systemInfoVo = new SystemInfoVo();
        systemInfoVo.setSystemId(sysSystemPo.getId());
        systemInfoVo.setSystemCode(sysSystemPo.getCode());
        systemInfoVo.setSystemName(sysSystemPo.getName());
        Context.set(SYSTEM_INFO,systemInfoVo);

    }
}
