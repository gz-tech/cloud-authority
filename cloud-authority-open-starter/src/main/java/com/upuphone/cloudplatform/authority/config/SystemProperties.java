package com.upuphone.cloudplatform.authority.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 权限系统的注册信息
 */
@Data
@ConfigurationProperties("authority.system")
@Component
public class SystemProperties {
    @Value("${authority.system.code}")
    private String code;
    @Value("${authority.system.secret}")
    private String secret;
}
