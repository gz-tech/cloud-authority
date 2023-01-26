package com.upuphone.cloudplatform.authority.config;

import com.upuphone.cloudplatform.authority.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname AuthorityInterceptorConfig
 * @Description TODO
 * @Date 2022/5/29 8:07 下午
 * @Created by gz-d
 */
@Configuration
public class AuthorityInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/**");
    }
}
