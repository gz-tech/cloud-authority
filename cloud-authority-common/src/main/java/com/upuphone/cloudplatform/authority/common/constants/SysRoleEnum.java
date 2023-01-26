package com.upuphone.cloudplatform.authority.common.constants;

import java.util.HashMap;
import java.util.Map;

public enum SysRoleEnum {

    /**
     * 超管
     */
    SYS_ADMIN(0),
    /**
     * 系统owner
     */
    SYS_OWNER(1),
    /**
     * 系统开发
     */
    SYS_DEVELOPER(2),
    ;

    private final Integer roleType;

    private static final Map<Integer, SysRoleEnum> CODE_MAP = new HashMap<>();

    static {
        for (SysRoleEnum value : values()) {
            CODE_MAP.put(value.roleType, value);
        }
    }

    SysRoleEnum(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public static SysRoleEnum getSysRoleByType(Integer roleType) {
        return CODE_MAP.get(roleType);
    }
}
