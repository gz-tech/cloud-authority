package com.upuphone.cloudplatform.authority.api.vo.errorcode;

import com.upuphone.cloudplatform.common.response.ErrorCode;

public enum AuthSdkErrorCode implements ErrorCode {

    NOT_PRIVILEGE(153000, "无权限，拒绝访问"),
    RESOURECE_NO_FOUND(153001, "资源不存在"),
    PERMISSION_NO_FOUND(153002, "权限不存在"),

    ;

    private final int errorCode;
    private final String errorMessage;


    AuthSdkErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

}
