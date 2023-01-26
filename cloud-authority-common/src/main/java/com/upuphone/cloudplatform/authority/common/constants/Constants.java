package com.upuphone.cloudplatform.authority.common.constants;

/**
 * Description:
 *
 * @author hanzhumeng
 * Created: 2022/3/10
 */
public final class Constants {

    /**
     * accessToken http头
     */
    public static final String AUTH_HEADER = "Authorization";
    /**
     * accessToken http头内容前缀
     */
    public static final String AUTH_PREFIX = "bearer ";
    /**
     * 用户详情hashKey
     */
    public static final String HASH_KEY_USER_DETAILS = "details";
    /**
     * 用户当前生效accessToken hashKey
     */
    public static final String HASH_KEY_USER_ACCESS_TOKEN = "access_token";
    /**
     * 用户持有内部接口权限 hashKey
     */
    public static final String HASH_KEY_USER_PERMISSION = "permissions";
    /**
     * 用户持有内部资源权限 hashKey
     */
    public static final String HASH_KEY_USER_RESOURCES = "resources";

    private Constants() {
    }

}
