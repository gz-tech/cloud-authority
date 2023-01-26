package com.upuphone.cloudplatform.authority.errorcode;

import com.upuphone.cloudplatform.common.response.ErrorCode;

public enum AuthErrorCode implements ErrorCode {

    USER_NOT_FOUND(152000, "未找到用户"),
    NOT_LOGGED_IN(152001, "未登录"),
    ACCESS_DENIED(152002, "无权限，拒绝访问"),
    ILLEGAL_STATE(152003, "请求数据资源状态异常"),
    AUTH_REQUEST_FAILED(152004, "请求认证服务器失败"),
    USER_BLOCKED(152005, "用户被锁定"),
    USER_UID_DUPLICATED(152006, "用户uid重复"),
    BIZ_ROLE_CODE_DUPLICATED(152007, "业务角色Code重复"),
    SYSTEM_NOT_FOUND(152008, "应用未找到"),
    ;

    private final int errorCode;
    private final String errorMessage;


    AuthErrorCode(int errorCode, String errorMessage) {
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
