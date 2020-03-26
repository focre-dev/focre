package com.focre.adminrest.config;

import com.focre.base.config.GlobalProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = GlobalProperties.FOCRE_PREFIX)
public class RestProperties extends GlobalProperties {

    /** 不是需要登录访问url集合 */
    private List<String> notAuthPath;

    /** 不是需要客户端类型url集合 */
    private List<String> notClientAuthPath;

    /** 客户端时间和服务端时间差(秒) */
    protected Long tsDiffer = 3 * 60L;
}
