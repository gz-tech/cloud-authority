package com.upuphone.cloudplatform.authority.common.constants;

public enum BizActionTypeEnum {
    /**
     * 菜单
     */
    QUERY(0, "QUERY"),

    ;


    private final Integer intType;

    private final String typeStr;


    public static BizActionTypeEnum getByTypeStr(String typeStr) {
        for (BizActionTypeEnum bizActionTypeEnum : BizActionTypeEnum.values()) {
            if (bizActionTypeEnum.getTypeStr().equals(typeStr)) {
                return bizActionTypeEnum;
            }
        }
        return null;
    }

    BizActionTypeEnum(Integer intType, String typeStr) {
        this.intType = intType;
        this.typeStr = typeStr;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public Integer getIntType() {
        return intType;
    }


}
