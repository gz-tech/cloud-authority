package com.upuphone.cloudplatform.authority.business.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "oauth2.client")
public class Oauth2Settings {

    private String clientId;

    private String clientSecret;

    private String authorizationGrantType;

    private String redirectUri;

    private String userNameAttribute;

    /**
     * 拼接获取授权码链接
     */
    private String authorizationUri;

    /**
     * 获取accessToken链接
     */
    private String tokenUri;

    /**
     * 获取用户信息链接
     */
    private String userInfoUri;

    private Integer stateStoreDuration;

    /**
     * 验证accessToken有效性链接
     */
    private String checkAccessTokenUri;

    /**
     * 注销accessToken链接
     */
    private String sessionRemoveUri;

}
