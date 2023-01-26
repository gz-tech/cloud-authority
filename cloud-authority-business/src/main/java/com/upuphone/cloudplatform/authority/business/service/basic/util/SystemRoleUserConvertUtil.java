package com.upuphone.cloudplatform.authority.business.service.basic.util;

import com.upuphone.cloudplatform.authority.mybatis.entity.SysRoleUserPo;
import com.upuphone.cloudplatform.authority.vo.response.DeveloperDetailVo;
import com.upuphone.cloudplatform.authority.vo.response.user.SystemRoleUserDetail;
import com.upuphone.cloudplatform.common.exception.ServiceException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;

/**
 * @Author Min.Jiang
 * @Date 2022/5/24 11:03
 * @Version 1.0
 */
public class SystemRoleUserConvertUtil {
    public static SystemRoleUserDetail convertFromPo(SysRoleUserPo sysRoleUserPo) {
        if (sysRoleUserPo == null) {
            throw new ServiceException(CommonErrorCode.PARAM_ERROR);
        }
        SystemRoleUserDetail sysRoleUser = new SystemRoleUserDetail();
        sysRoleUser.setId(sysRoleUserPo.getId() + "");
        sysRoleUser.setUserId(sysRoleUserPo.getUserId() + "");
        return sysRoleUser;
    }
}
