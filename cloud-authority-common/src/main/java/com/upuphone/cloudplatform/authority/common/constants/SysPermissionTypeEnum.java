package com.upuphone.cloudplatform.authority.common.constants;

public enum SysPermissionTypeEnum {

    /**
     * 后端接口
     */
    INTERFACE(0),
    /**
     * 前端路由
     */
    ROUTE(1),
    /**
     * 菜单
     */
    MENU(2),
    ;


    private final Integer resType;

    SysPermissionTypeEnum(Integer resType) {
        this.resType = resType;
    }

    public Integer getResType() {
        return resType;
    }
}
