package com.upuphone.cloudplatform.authority.common.constants;

import java.util.HashMap;
import java.util.Map;

public enum SysRelationEnum {

    /*
    *   只是代表资源的类型，具体resource_id根据资源类型不同取不同表的值
    *   故这里的值与role类型并非一个含义
    */

    /**
     * 系统owner
     */
    SYSTEM_OWNER(1),
    /**
     * 系统开发
     */
    SYSTEM_DEVELOPER(2),
    ;

    private final Integer type;

    private static final Map<Integer, SysRelationEnum> TYPE_MAP = new HashMap<>();

    static {
        for (SysRelationEnum value : values()) {
            TYPE_MAP.put(value.getType(), value);
        }
    }

    SysRelationEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static SysRelationEnum getByType(Integer type) {
        return TYPE_MAP.get(type);
    }

}
