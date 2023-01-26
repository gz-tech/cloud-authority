package com.upuphone.cloudplatform.authority.business.service.role;

import com.upuphone.cloudplatform.authority.business.repo.IBizRoleService;
import com.upuphone.cloudplatform.authority.business.service.common.BaseLockService;
import com.upuphone.cloudplatform.authority.common.constants.LockTypeEnum;
import com.upuphone.cloudplatform.authority.common.constants.RedisKeys;
import com.upuphone.cloudplatform.authority.errorcode.AuthErrorCode;
import com.upuphone.cloudplatform.authority.vo.request.role.BizRoleDeleteRequest;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BizRoleDeleteService extends BaseLockService<BizRoleDeleteRequest, Void> {

    @Autowired
    private IBizRoleService bizRoleService;

    @Override
    protected String getLockKey(BizRoleDeleteRequest request) {
        return RedisKeys.getBizLockKey(LockTypeEnum.BIZ_ROLE, request.getSystemId(), request.getRoleId());
    }

    @Override
    protected void validate(BizRoleDeleteRequest bizRoleDeleteRequest) {

    }

    @Override
    protected Void processCore(BizRoleDeleteRequest bizRoleDeleteRequest) throws Exception {
        Long roleId = Long.valueOf(bizRoleDeleteRequest.getRoleId());
        Long systemId = Long.valueOf(bizRoleDeleteRequest.getSystemId());
        if (!bizRoleService.deleteRole(roleId, systemId)) {
            log.error("[BizRoleDeleteService] 删除角色失败, req={}", JsonUtility.toJson(bizRoleDeleteRequest));
            throw new BusinessException(AuthErrorCode.ILLEGAL_STATE);
        }
        return null;
    }
}
