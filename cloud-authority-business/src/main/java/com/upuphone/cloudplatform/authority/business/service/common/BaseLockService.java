package com.upuphone.cloudplatform.authority.business.service.common;

import com.upuphone.cloudplatform.common.component.BaseService;
import com.upuphone.cloudplatform.common.exception.BusinessException;
import com.upuphone.cloudplatform.common.response.CommonErrorCode;
import com.upuphone.cloudplatform.common.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class BaseLockService<RQ, RP> extends BaseService<RQ, RP> {

    @Autowired
    private RedissonClient redissonClient;

    private static final long WAIT_TIME = 0L;

    private static final long LEASE_TIME = 15L;

    protected abstract String getLockKey(RQ request);

    protected long getWaitTime() {
        return WAIT_TIME;
    }

    protected long getLeaseTime() {
        return LEASE_TIME;
    }

    @Override
    public RP process(RQ request) {
        validate(request);
        String lockKey = getLockKey(request);
        if (StringUtils.isBlank(lockKey)) {
            throw new BusinessException(CommonErrorCode.PARAM_ERROR, "lockKey cannot be null");
        }
        RP innerResponse = null;
        RLock lock = null;
        try {
            lock = redissonClient.getLock(lockKey);
            if (!lock.tryLock(getWaitTime(), getLeaseTime(), TimeUnit.SECONDS)) {
                log.error("tryLock failed, lockKey=[{}], req={}", lockKey, JsonUtility.toJson(request));
                throw new BusinessException(CommonErrorCode.BUSINESS_ERROR);
            }
            innerResponse = processCore(request);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                log.warn("", e);
                throw (BusinessException) e;
            } else {
                log.error("", e);
            }
            handleException(request, e);
        } finally {
            if (null != lock && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return innerResponse;
    }
}
