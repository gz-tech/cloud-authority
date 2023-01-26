package com.upuphone.cloudplatform.authority.common.constants;

public enum BizResourceTypeEnum {
    /**
     * 菜单
     */
    MENU(0, "MENU"),

    /**
     * 后端接口
     */
    INTERFACE(1, "API"),

    /**
     * 前端路由
     */
    ROUTE(2, "ROUTE"),
    ;


    private final Integer resType;

    private final String typeStr;


    public static BizResourceTypeEnum getByTypeStr(String typeStr) {
        for (BizResourceTypeEnum bizResourceTypeEnum : BizResourceTypeEnum.values()) {
            if (bizResourceTypeEnum.getTypeStr().equals(typeStr)) {
                return bizResourceTypeEnum;
            }
        }
        return null;
    }

    BizResourceTypeEnum(Integer resType, String typeStr) {
        this.resType = resType;
        this.typeStr = typeStr;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public Integer getResType() {
        return resType;
    }


}
