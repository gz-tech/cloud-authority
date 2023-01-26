package com.upuphone.cloudplatform.authority.business.service.role;

import com.upuphone.cloudplatform.authority.business.repo.IBizRoleService;
import com.upuphone.cloudplatform.authority.errorcode.AuthErrorCode;
import com.upuphone.cloudplatform.authority.vo.request.role.BizRoleAddRequest;
import com.upuphone.cloudplatform.common.component.BaseService;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BizRoleAddService extends BaseService<BizRoleAddRequest, Void> {

    @Autowired
    private IBizRoleService bizRoleService;

    @Override
    protected void validate(BizRoleAddRequest bizRoleAddRequest) {

    }

    @Override
    protected Void processCore(BizRoleAddRequest bizRoleAddRequest) throws Exception {
        Long systemId = Long.valueOf(bizRoleAddRequest.getSystemId());
        String code = bizRoleAddRequest.getCode();
        String description = bizRoleAddRequest.getDescription();
        if (!bizRoleService.addRole(systemId, code, description)) {
            log.error("[BizRoleAddService] 增加角色失败, req={}", JsonUtility.toJson(bizRoleAddRequest));
            throw new BusinessException(AuthErrorCode.ILLEGAL_STATE);
        }
        return null;
    }
}
