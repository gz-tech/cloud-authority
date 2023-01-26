package com.upuphone.cloudplatform.authority;

import com.upuphone.cloudplatform.authority.api.vo.errorcode.AuthSdkErrorCode;
import com.upuphone.cloudplatform.common.response.CommonResponse;
import com.upuphone.cloudplatform.common.utils.JsonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

public class ResponseWrite {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseWrite.class);

    public static void writeResult(HttpServletResponse response, AuthSdkErrorCode errorCode) {
        try {
            response.reset();
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JsonUtility.toJson(CommonResponse.fail(errorCode.getErrorCode(), errorCode.getErrorMessage(), "")));
        } catch (Exception e) {
            LOGGER.error("writeResult error", e);
        }
    }
}
