package com.upuphone.cloudplatform.authority.business.service.user;

import com.upuphone.cloudplatform.authority.business.repo.IBizRoleUserService;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserDeleteRoleRequest;
import com.upuphone.cloudplatform.common.component.BaseService;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import com.upuphone.cloudplatform.common.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BizUserRoleDeleteService extends BaseService<BizUserDeleteRoleRequest, Void> {

    @Autowired
    private IBizRoleUserService bizRoleUserService;

    @Override
    protected void validate(BizUserDeleteRoleRequest bizUserDeleteRoleRequest) {
        Long systemId = Long.valueOf(bizUserDeleteRoleRequest.getSystemId());
        String roleUserId = bizUserDeleteRoleRequest.getId();
        bizRoleUserService.checkValidBizRoleUserAssociation(roleUserId, systemId);
    }

    @Override
    protected Void processCore(BizUserDeleteRoleRequest bizUserDeleteRoleRequest) throws Exception {
        if (!bizRoleUserService.removeById(bizUserDeleteRoleRequest.getId())) {
            log.error("[BizUserRoleDeleteService] 删除角色失败, req={}", JsonUtility.toJson(bizUserDeleteRoleRequest));
            throw new BusinessException(CommonErrorCode.BUSINESS_ERROR);
        }
        return null;
    }
}
