package com.upuphone.cloudplatform.authority.business.service.role;

import com.upuphone.cloudplatform.authority.business.repo.IBizRoleService;
import com.upuphone.cloudplatform.authority.business.service.common.BaseLockService;
import com.upuphone.cloudplatform.authority.common.constants.LockTypeEnum;
import com.upuphone.cloudplatform.authority.common.constants.RedisKeys;
import com.upuphone.cloudplatform.authority.errorcode.AuthErrorCode;
import com.upuphone.cloudplatform.authority.vo.request.role.BizRoleModifyRequest;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BizRoleModifyService extends BaseLockService<BizRoleModifyRequest, Void> {

    @Autowired
    private IBizRoleService bizRoleService;

    @Override
    protected void validate(BizRoleModifyRequest bizRoleModifyRequest) {

    }

    @Override
    protected Void processCore(BizRoleModifyRequest bizRoleModifyRequest) throws Exception {
        Long roleId = Long.valueOf(bizRoleModifyRequest.getId());
        Long systemId = Long.valueOf(bizRoleModifyRequest.getSystemId());
        String code = bizRoleModifyRequest.getCode();
        String description = bizRoleModifyRequest.getDescription();
        if (!bizRoleService.modifyRole(roleId, systemId, code, description)) {
            log.error("[BizRoleModifyService] 更新角色失败, req={}", JsonUtility.toJson(bizRoleModifyRequest));
            throw new BusinessException(AuthErrorCode.ILLEGAL_STATE);
        }
        return null;
    }

    @Override
    protected String getLockKey(BizRoleModifyRequest request) {
        return RedisKeys.getBizLockKey(LockTypeEnum.BIZ_ROLE, request.getSystemId(), request.getId());
    }
}
