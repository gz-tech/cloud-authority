package com.upuphone.cloudplatform.authority.business.entity;

import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum PermissionActionEnum {
    QUERY(0, "查询");

    private Integer action;

    private String actionName;

    PermissionActionEnum(Integer action, String actionName) {
        this.action = action;
        this.actionName = actionName;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    /**
     * 根据code获取去value
     * @param action
     * @return
     */
    public static String getActionNameByAction(Integer action) {
        for(PermissionActionEnum permission : PermissionActionEnum.values()){
            if(action.equals(permission.getAction())){
                return permission.getActionName();
            }
        }
        log.error("不存在action: {}", action);
        throw new BusinessException(CommonErrorCode.SERVICE_ERROR);
    }
}
