package com.upuphone.cloudplatform.authority.security;

import com.upuphone.cloudplatform.authority.business.security.PermissionUtil;
import com.upuphone.cloudplatform.authority.common.annotations.Authorization;
import com.upuphone.cloudplatform.authority.errorcode.AuthErrorCode;
import com.upuphone.cloudplatform.authority.utils.SpelUtil;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
@Aspect
@Order(1)
public class PermissionInterceptor {

    @Autowired
    private PermissionUtil permissionUtil;

    @Pointcut("@annotation(com.upuphone.cloudplatform.authority.common.annotations.Authorization)")
    public void authPointCut() {
    }

    @Before("authPointCut()")
    public void around(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Authorization annotation = method.getAnnotation(Authorization.class);
        if (null == annotation) {
            return;
        }
        String permissionCode = annotation.permissionCode();
        if (StringUtils.isBlank(permissionCode)) {
            return;
        }
        String elKey = annotation.systemId();
        String systemId = null;
        if (!StringUtils.isBlank(elKey)) {
            systemId = (String) SpelUtil.generateKeyBySpEL(elKey, joinPoint);
        }
        if (!permissionUtil.hasSysPermission(permissionCode, systemId)) {
            throw new BusinessException(AuthErrorCode.ACCESS_DENIED);
        }
    }

}
