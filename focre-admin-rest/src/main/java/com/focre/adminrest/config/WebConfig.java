package com.focre.adminrest.config;

import com.focre.adminrest.config.filter.TokenAuthFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description [WEB配置]
 * @title  ye21st ye21st@gmail.com
 * @author ye21st
 * @date 2020/3/28
 * @time 5:46 下午
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public TokenAuthFilter tokenAuthFilter() {
        return new TokenAuthFilter();
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//
            .allowedOrigins("*")//
            .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")//
            .allowedHeaders("*")//
            .exposedHeaders("Content-Type,Content-Length, Authorization, Accept,X-Requested-With,exchange-token,exchange-language,exchange-client")
            .allowCredentials(false).maxAge(86400);
    }
}
