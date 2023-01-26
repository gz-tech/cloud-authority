package com.upuphone.cloudplatform.authority.common.constants;

public enum LockTypeEnum {

    /**
     * 增加用户key :uid
     */
    BIZ_USER_ADD_UID("biz_user_add_uid"),
    /**
     * 增加用户角色key :userId
     */
    BIZ_USER_ADD_ROLE("biz_user_add_role"),
    /**
     * 角色相关key :roleId
     */
    BIZ_ROLE("biz_role"),
    ;

    private final String lockType;

    LockTypeEnum(String lockType) {
        this.lockType = lockType;
    }

    public String getLockType() {
        return lockType;
    }
}
