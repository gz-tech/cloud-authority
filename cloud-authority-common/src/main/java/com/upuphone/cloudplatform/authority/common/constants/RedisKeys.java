package com.upuphone.cloudplatform.authority.common.constants;

public final class RedisKeys {

    /**
     * 根据accessToken匹配用户ID
     */
    private static final String ACCESS_TOKEN_KEY = "ACCESS_TOKEN:%s";
    /**
     * 根据userId查询详情
     */
    private static final String USER_DETAIL_KEY = "USER_DETAIL:%s";
    /**
     * 锁通用key
     */
    private static final String BIZ_LOCK_KEY = "c:cloud-authority:LOCK:%s:%s:%s";

    private RedisKeys() {
    }

    public static String getAccessTokenKey(String token) {
        return String.format(ACCESS_TOKEN_KEY, token);
    }

    public static String getUserDetailKey(String userId) {
        return String.format(USER_DETAIL_KEY, userId);
    }

    public static String getBizLockKey(LockTypeEnum em, String systemId, String uniqueKey) {
        return String.format(BIZ_LOCK_KEY, em.getLockType(), systemId, uniqueKey);
    }

}
