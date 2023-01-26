package com.upuphone.cloudplatform.authority.business.security.open;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.base.Strings;
import com.upuphone.cloudplatform.authority.business.service.open.rbac.util.ContextUtil;
import com.upuphone.cloudplatform.authority.mybatis.entity.SysSystemPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.SysSystemMapper;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import com.upuphone.cloudplatform.common.utils.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: 授权服务
 *
 * @author hanzhumeng
 * Created: 2022/3/2
 */
@Component
@SuppressWarnings("ALL")
public class CheckSecretUtil {

    @Autowired
    private SysSystemMapper sysSystemMapper;


    public Boolean checkSecret(String systemCode, String secret) {
        if(Strings.isNullOrEmpty(systemCode) || Strings.isNullOrEmpty(secret)){
            throw new BusinessException(CommonErrorCode.PARAM_ERROR);
        }

        List<SysSystemPo> sysSystemPos = sysSystemMapper.selectList(Wrappers.<SysSystemPo>lambdaQuery()
                .eq(SysSystemPo::getCode,systemCode)
                .eq(SysSystemPo::getSecret,secret));

        if(!ListUtil.any(sysSystemPos)){
            return false;
        }

        ContextUtil.setSystemInfoToContext(sysSystemPos.get(0));

        return true;

    }

}
