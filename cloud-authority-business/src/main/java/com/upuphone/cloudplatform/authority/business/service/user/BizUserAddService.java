package com.upuphone.cloudplatform.authority.business.service.user;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.upuphone.cloudplatform.authority.business.service.common.BaseLockService;
import com.upuphone.cloudplatform.authority.business.converter.BizUserConverter;
import com.upuphone.cloudplatform.authority.common.constants.LockTypeEnum;
import com.upuphone.cloudplatform.authority.common.constants.RedisKeys;
import com.upuphone.cloudplatform.authority.errorcode.AuthErrorCode;
import com.upuphone.cloudplatform.authority.mybatis.entity.BizUserPo;
import com.upuphone.cloudplatform.authority.mybatis.mapper.BizUserMapper;
import com.upuphone.cloudplatform.authority.vo.request.user.BizUserAddRequest;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import com.upuphone.cloudplatform.common.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BizUserAddService extends BaseLockService<BizUserAddRequest, Void> {

    @Autowired
    private BizUserMapper bizUserMapper;

    @Override
    protected void validate(BizUserAddRequest bizUserAddRequest) {

    }

    @Override
    protected Void processCore(BizUserAddRequest bizUserAddRequest) throws Exception {
        String uid = bizUserAddRequest.getUid();
        Long systemId = Long.valueOf(bizUserAddRequest.getSystemId());
        long count = bizUserMapper.selectCount(Wrappers.<BizUserPo>lambdaQuery()
                .eq(BizUserPo::getUid, uid)
                .eq(BizUserPo::getSystemId, systemId));
        if (count > 0) {
            throw new BusinessException(AuthErrorCode.USER_UID_DUPLICATED);
        }
        BizUserPo po = BizUserConverter.INSTANCE.addRequest2Po(bizUserAddRequest);
        if (0 == bizUserMapper.insert(po)) {
            log.error("[UserAddService]新增用户失败, request={}", JsonUtility.toJson(bizUserAddRequest));
            throw new BusinessException(CommonErrorCode.BUSINESS_ERROR);
        }
        return null;
    }

    @Override
    protected String getLockKey(BizUserAddRequest request) {
        return RedisKeys.getBizLockKey(LockTypeEnum.BIZ_USER_ADD_UID, request.getSystemId(), request.getUid());
    }
}
