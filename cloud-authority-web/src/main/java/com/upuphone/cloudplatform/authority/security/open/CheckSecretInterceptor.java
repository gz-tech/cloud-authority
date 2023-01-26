package com.upuphone.cloudplatform.authority.security.open;

import com.upuphone.cloudplatform.authority.api.vo.errorcode.AuthSdkErrorCode;
import com.upuphone.cloudplatform.authority.api.vo.request.BaseRequest;
import com.upuphone.cloudplatform.authority.business.security.open.CheckSecretUtil;
import com.upuphone.cloudplatform.authority.security.open.model.CheckSecret;
import com.upuphone.cloudplatform.common.exception.BusinessException;
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
@Order(0)
public class CheckSecretInterceptor {

    @Autowired
    private CheckSecretUtil checkSecretUtil;


    @Pointcut("@annotation(com.upuphone.cloudplatform.authority.security.open.model.CheckSecret)")
    public void authPointCut() {
    }

    @Before("authPointCut()")
    public void before(JoinPoint joinPoint) {


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CheckSecret annotation = method.getAnnotation(CheckSecret.class);
        if (null == annotation) {
            return;
        }

        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            throw new BusinessException(AuthSdkErrorCode.NOT_PRIVILEGE);
        }

        Object arg0 = args[0];

        if (!BaseRequest.class.isAssignableFrom(arg0.getClass())) {
            throw new BusinessException(AuthSdkErrorCode.NOT_PRIVILEGE);
        }

        BaseRequest baseRequest = (BaseRequest) arg0;
        Boolean isOk = checkSecretUtil
                .checkSecret(baseRequest.getSystemCode(), baseRequest.getSecret());
        if (!isOk) {
            throw new BusinessException(AuthSdkErrorCode.NOT_PRIVILEGE);
        }

    }

}
