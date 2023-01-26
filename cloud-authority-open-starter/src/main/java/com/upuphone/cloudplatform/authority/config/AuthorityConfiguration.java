package com.upuphone.cloudplatform.authority.config;

import com.upuphone.cloudplatform.authority.interceptor.AuthorizationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname PermissionConfiguration
 * @Description TODO
 * @Date 2022/5/24 5:14 下午
 * @Created by gz-d
 */
@Slf4j
@ConditionalOnClass({AuthorizationInterceptor.class})
@ConditionalOnWebApplication
@Configuration
@EnableConfigurationProperties(SystemProperties.class)
@AutoConfigureBefore({WebMvcAutoConfiguration.EnableWebMvcConfiguration.class})
public class AuthorityConfiguration {
    @Autowired
    private SystemProperties systemProperties;

    @Bean(name = "permissionInterceptor")
    public AuthorizationInterceptor getAuthorizationInterceptor() {
        AuthorizationInterceptor authorizationInterceptor = new AuthorizationInterceptor();
        authorizationInterceptor.setSystemProperties(systemProperties);
        return authorizationInterceptor;
    }

    public AuthorityConfiguration() {
        log.info("AuthorityConfiguration running...");
    }
}
