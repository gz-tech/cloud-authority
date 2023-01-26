package com.upuphone.cloudplatform.authority.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Oauth2Config {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
