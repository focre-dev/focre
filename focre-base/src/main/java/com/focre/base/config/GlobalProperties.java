package com.focre.base.config;

import lombok.Data;

import java.util.List;


/**
 * @author ye21st
 */
@Data
public class GlobalProperties {

    public static final String FOCRE_PREFIX = "focre";

    /** 前端签名自定义请求头 */
    protected String tokenHeader = "Authorization";

    /** Token前缀 */
    protected String tokenPrefix = "Bearer";

    /** 客户端类型请求头 */
    protected String clientHeader = "Client-Type";

    /** token生成混淆key */
    protected String secret = "defaultSecret";

    /** token有效时长(秒) */
    protected Long expiration = 24 * 60 * 60L;

    /** 单设备登录客户端, 如果为空则不需要控制单设备登录 */
    private List<String> singleLoginClient;

    /** 前端签名混淆key */
    protected String randomKey = "randomKey";

    /** DES加密混淆key */
    protected String desKey = "desKeyck";

    /** 是否开启swagger文档 */
    private Boolean enableSwagger = false;

    /** 如果使用FastDFS则填写FastDFS访问地址(例如:http://11.20.33.44/) */
    private String filePath;
    
    /** 项目名称(主要区分同环境不同项目的服务) */
    private String projectName;
}
