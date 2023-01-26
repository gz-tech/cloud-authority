package com.upuphone.cloudplatform.authority.interceptor;
import com.upuphone.cloudplatform.authority.Authorization;
import com.upuphone.cloudplatform.authority.ResponseWrite;
import com.upuphone.cloudplatform.authority.api.rbac.RbacAuthorityClient;
import com.upuphone.cloudplatform.authority.api.vo.errorcode.AuthSdkErrorCode;
import com.upuphone.cloudplatform.authority.api.vo.request.CheckAllowRequest;
import com.upuphone.cloudplatform.authority.api.vo.response.CheckAllowResponse;
import com.upuphone.cloudplatform.authority.config.SystemProperties;
import com.upuphone.cloudplatform.common.context.RequestContext;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import com.upuphone.cloudplatform.common.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 权限校验拦截器
 */
@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    private static final List<String> IGNORE_URL_LIST = new ArrayList<>();
    private SystemProperties systemProperties;
    private RbacAuthorityClient rbacAuthorityClient;

    public SystemProperties getSystemProperties() {
        return systemProperties;
    }

    public void setSystemProperties(SystemProperties systemProperties) {
        this.systemProperties = systemProperties;
    }

    static {
        IGNORE_URL_LIST.add("checkHealth");
        IGNORE_URL_LIST.add("swagger");
        IGNORE_URL_LIST.add("doc");
        IGNORE_URL_LIST.add("webjars");
    }
    private boolean isWhiteListPath(String path) {
        if (path == null) {
            return true;
        }
        return IGNORE_URL_LIST.stream().anyMatch(path::contains);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();

        if (isWhiteListPath(servletPath)) {
            return true;
        }

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (null == handlerMethod.getMethodAnnotation(Authorization.class)) {
            return true;
        }
        rbacAuthorityClient = SpringUtil.getBean(RbacAuthorityClient.class);
        try {
            CheckAllowRequest checkAllowRequest = new CheckAllowRequest();
            checkAllowRequest.setUserId(RequestContext.getOperator());
            checkAllowRequest.setSystemCode(systemProperties.getCode());
            checkAllowRequest.setSecret(systemProperties.getSecret());
            checkAllowRequest.setAction("QUERY");
            CheckAllowRequest.ResourceRequest resourceRequest = new CheckAllowRequest.ResourceRequest();
            resourceRequest.setResourceValue(servletPath);
            resourceRequest.setResourceType("API");
            checkAllowRequest.setResourceRequest(resourceRequest);
            CommonResponse<CheckAllowResponse> checkAllowResponse = rbacAuthorityClient.isAllowed(checkAllowRequest);
            if (isErrorRes(checkAllowResponse) || !checkAllowResponse.getData().isAllow()) {
                log.info("无权限");
                ResponseWrite.writeResult(response, AuthSdkErrorCode.NOT_PRIVILEGE);
                return false;
            }
        } catch (Exception e) {
            log.error("PermissionInterceptor.rbacAuthorityClient#isAllowed error,", e);
            ResponseWrite.writeResult(response, AuthSdkErrorCode.NOT_PRIVILEGE);
            return false;
        }
        return true;
    }
    public boolean isErrorRes(CommonResponse result) {
        return Objects.isNull(result) || CommonErrorCode.SUCCESS.getErrorCode() != result.getCode();
    }
}
