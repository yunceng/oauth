package com.wyn.oauth.resource.config;

import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.util.Assert;

import javax.sql.DataSource;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)   //启用权限认证    @PreAuthorize，@PostAuthorize，@Secured
public class OAuth2ResourceConfig  extends ResourceServerConfigurerAdapter {

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Autowired
    private AuthorizationServerProperties authorizationServerProperties;

    @Autowired
    private DataSource dataSource;

    @Bean // 声明TokenStore实现
    public TokenStore tokenStore() {
        Assert.notNull(dataSource, "DataSource must be provided");
        return new JdbcTokenStore(dataSource);
    }

    @Bean // 声明 ClientDetails实现
    public ClientDetailsService clientDetails() {
        Assert.notNull(dataSource, "DataSource must be provided");
        return new JdbcClientDetailsService(dataSource);
    }

    @Primary
    @Bean
    public RemoteTokenServices tokenServices() {

        // 1、通过向认证服务器发送token验证请求，来获得认证信息
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(authorizationServerProperties.getCheckTokenAccess());
        tokenService.setClientId(oAuth2ClientProperties.getClientId());
        tokenService.setClientSecret(oAuth2ClientProperties.getClientSecret());
        tokenService.setAccessTokenConverter(accessTokenConverter());

//        // 2、通过token自己访问数据库来获取认证信息
//        final DefaultTokenServices tokenService = new DefaultTokenServices();
//        tokenService.setTokenStore(tokenStore());
//        tokenService.setSupportRefreshToken(true);
//        tokenService.setClientDetailsService(clientDetails());
        return tokenService;
    }

    @Bean
    public AccessTokenConverter accessTokenConverter() {
        return new DefaultAccessTokenConverter();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests().antMatchers("/oauth/**", "/e/**", "/login").permitAll()   // '/oauth/**','/e/**', '/login' 不需要token就可以访问
                .anyRequest().authenticated()   //其他的所有请求都需要通过验证token来访问，来获取认证信息
                .and().authorizeRequests().filterSecurityInterceptorOncePerRequest(true)
                .and().headers().frameOptions().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }
}
