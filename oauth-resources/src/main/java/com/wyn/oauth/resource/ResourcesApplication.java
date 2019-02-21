package com.wyn.oauth.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableOAuth2Client
@EnableConfigurationProperties(value = {OAuth2ClientProperties.class, AuthorizationServerProperties.class})
@EnableAutoConfiguration
public class ResourcesApplication {
    public static void main(String[] args){
        SpringApplication.run(ResourcesApplication.class, args);
    }
}
