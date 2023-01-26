package com.upuphone.cloudplatform.authority.business.service.user;

import com.upuphone.cloudplatform.authority.business.repo.IBizRoleUserService;
import com.upuphone.cloudplatform.authority.business.service.common.BaseLockService;
import com.upuphone.cloudplatform.authority.common.constants.LockTypeEnum;
import com.upuphone.cloudplatform.authority.common.constants.RedisKeys;
import com.upuphone.cloudplatform.authority.errorcode.AuthErrorCode;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserAddRoleRequest;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BizUserRoleAddService extends BaseLockService<BizUserAddRoleRequest, Void> {

    @Autowired
    private IBizRoleUserService bizRoleUserService;

    @Override
    protected String getLockKey(BizUserAddRoleRequest request) {
        return RedisKeys.getBizLockKey(LockTypeEnum.BIZ_USER_ADD_ROLE, request.getSystemId(), request.getUserId());
    }

    @Override
    protected void validate(BizUserAddRoleRequest request) {

    }

    @Override
    protected Void processCore(BizUserAddRoleRequest soaRequest) throws Exception {
        String userId = soaRequest.getUserId();
        Long systemId = Long.valueOf(soaRequest.getSystemId());
        Set<Long> addRoleIds = soaRequest.getRoleIds().stream().map(Long::valueOf).collect(Collectors.toSet());
        if (!bizRoleUserService.addUserRole(addRoleIds, userId, systemId)) {
            log.error("[BizUserRoleAddService] 新增业务用户角色失败, request={}", JsonUtility.toJson(soaRequest));
            throw new BusinessException(AuthErrorCode.ILLEGAL_STATE);
        }
        return null;
    }
}
