package com.focre.adminrest.config;

import com.focre.base.config.GlobalProperties;
import com.google.common.base.Predicates;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SwaggerConfig
 * @Description: SwaggerConfig
 * @Author ye21st
 * @Date 2019-07-15 12:58:19
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = GlobalProperties.FOCRE_PREFIX, value = { "enable-swagger" }, havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
                .paths(PathSelectors.any())
                .build();
        //.globalOperationParameters(setHeaderToken())
    }
}
