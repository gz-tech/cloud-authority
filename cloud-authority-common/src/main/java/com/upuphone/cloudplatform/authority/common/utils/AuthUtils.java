package com.upuphone.cloudplatform.authority.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * OAuth2工具类
 */
public final class AuthUtils {

    private static final List<String> IGNORE_URL_LIST = new ArrayList<>();

    /**
     * 获取认证code URL
     */
    private static final String AUTHORIZE_URL = "%s?client_id=%s&response_type=code&redirect_uri=%s&state=%s";

    /**
     * 获取accessToken URL
     */
    private static final String TOKEN_URL = "%s?grant_type=authorization_code&client_id=%s&client_secret=%s&code=%s&redirect_uri=%s";

    /**
     * 获取USER信息 URL
     */
    private static final String USER_INFO_URL = "%s?access_token=%s";

    static {
        IGNORE_URL_LIST.add("checkHealth");
        IGNORE_URL_LIST.add("swagger");
        IGNORE_URL_LIST.add("doc");
        IGNORE_URL_LIST.add("webjars");
        IGNORE_URL_LIST.add("error");
    }

    private AuthUtils() {
    }

    public static boolean isIllegalPath(String path) {
        if (StringUtils.isBlank(path)) {
            return true;
        }
        return IGNORE_URL_LIST.stream().anyMatch(path::contains);
    }

    public static String getAuthorizeUrl(String url, String clientId, String redirectUrl, String state) {
        return String.format(AUTHORIZE_URL, url, clientId, redirectUrl, state);
    }

    public static String getTokenUrl(String url, String clientId, String clientSecret, String code, String redirectUri) {
        return String.format(TOKEN_URL, url, clientId, clientSecret, code, redirectUri);
    }

    public static String getUserInfoUrl(String url, String accessToken) {
        return String.format(USER_INFO_URL, url, accessToken);
    }
}
