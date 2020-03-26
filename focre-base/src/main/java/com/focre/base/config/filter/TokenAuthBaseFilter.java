package com.focre.base.config.filter;

import com.focre.base.config.GlobalProperties;
import com.focre.base.exception.BizExceptionEnum;
import com.focre.base.i18n.consts.CommonMessage;
import com.focre.base.jwt.JwtTokenService;
import com.focre.base.redis.RedisKeyEnum;
import com.focre.base.redis.RedisService;
import io.jsonwebtoken.JwtException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 对客户端请求的jwt token验证过滤器
 */
public abstract class TokenAuthBaseFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(TokenAuthBaseFilter.class);

    @Autowired
    private JwtTokenService jwtTokenServiceImpl;

    @Autowired
    private GlobalProperties properties;

    @Autowired
    private RedisService redisServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        String uri = request.getServletPath();

        if ("OPTIONS".equals(request.getMethod()) || uri.startsWith("/v2") || uri.startsWith("/webjars")
            || uri.startsWith("/swagger") ||  uri.startsWith("/druid")) {
            chain.doFilter(request, response);
            return;
        }

        /** 不是需要客户端类型url集合 */
        List<String> notClientAuthPath = getNotClientAuthPath();
        if (CollectionUtils.isNotEmpty(notClientAuthPath) && notClientAuthPath.contains(uri)) {
            // 跳过拦截
            chain.doFilter(request, response);
            return;
        }

        final String clientHeader = request.getHeader(properties.getClientHeader());
        // 判断头上是否携带了有效客户端类型
        if (StringUtils.isBlank(clientHeader)) {
            log.warn("授权验证---- 用户未携带客户端类型");
            renderJson(response, BizExceptionEnum.CLIENT_TYPE_ERROR, CommonMessage.CLIENT_TYPE_ERROR);
            return;
        }

        if (!checkClientType(clientHeader)) {
            log.warn("授权验证 ---- 用户携带无效客户端类型：{}", clientHeader);
            renderJson(response, BizExceptionEnum.CLIENT_TYPE_ERROR, CommonMessage.CLIENT_TYPE_ERROR);
            return;
        }

        final String tokenHeader = request.getHeader(properties.getTokenHeader());
        // 判断头上是否携带了token
        if (StringUtils.isBlank(tokenHeader) || !tokenHeader.startsWith(properties.getTokenPrefix())) {
            log.warn("授权验证---- 用户未携带有效Token前缀  用户Token:{}, 系统配置Token前缀:{}, 请求地址:{}", tokenHeader,
                properties.getTokenPrefix(), uri);
            renderJson(response, BizExceptionEnum.TOKEN_ERROR, CommonMessage.TOKEN_ERROR);
            return;
        }

        // Token前缀长度
        int tokenPrefixlength = properties.getTokenPrefix().length() + 1;

        // 判断头上是否携带有效token
        if (tokenHeader.length() <= tokenPrefixlength) {
            /** 不是需要登录访问url集合 */
            List<String> notAuthPath = getNotAuthPath();

            // 配置了非授权依然可以范围
            if (CollectionUtils.isNotEmpty(notAuthPath) && notAuthPath.contains(uri)) {
                // 如果请求头中未携带了token，则跳过拦截
                chain.doFilter(request, response);
                return;
            }

            // 前缀匹配
            for (String path : notAuthPath) {
                if (path.endsWith("*") && uri.startsWith(path.substring(0, path.length() - 1))) {
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        // 判断头上是否携带有效token
        if (tokenHeader.length() <= tokenPrefixlength) {
            log.warn("授权验证---- 用户未携带有效Token 用户Token:{}, 系统配置Token前缀:{}, 请求地址:{}", tokenHeader,
                properties.getTokenPrefix(), uri);
            renderJson(response, BizExceptionEnum.TOKEN_ERROR, CommonMessage.TOKEN_ERROR);
            return;
        }

        // 截取Token内容
        String authToken = tokenHeader.substring(tokenPrefixlength);

        // 验证token是否过期
        try {
            boolean flag = jwtTokenServiceImpl.isTokenExpired(authToken);
            if (flag) {
                renderJson(response, BizExceptionEnum.TOKEN_EXPIRED, CommonMessage.TOKEN_EXPIRED);
                return;
            }
        } catch (JwtException e) {
            // 有异常就是token解析失败
            renderJson(response, BizExceptionEnum.TOKEN_ERROR, CommonMessage.TOKEN_ERROR);
            return;
        }

        // 获取客户端类型
        String clientType = jwtTokenServiceImpl.getClientTypeFromToken(authToken);
        if (!clientType.equals(clientHeader)) {
            log.warn("授权验证---- 用户携带有效Token绑定客户端与携带客户端类型不一致  Token绑定客户端:{}, 携带客户端:{}", clientType, clientHeader);
            renderJson(response, BizExceptionEnum.CLIENT_TYPE_ERROR, CommonMessage.CLIENT_TYPE_ERROR);
            return;
        }

        // 如果开启单设备登录，则需要将Token和Redis中的Token进行对比
        if (CollectionUtils.isNotEmpty(properties.getSingleLoginClient())) {

            // 如果客户端类型单设备登录配置中，则需要验证访问token是否有效
            if (properties.getSingleLoginClient().contains(clientType)) {
                // 获取用户ID
                String userId = jwtTokenServiceImpl.getUserIdFromToken(authToken);

                // 获取存储在Redis中有效Token
                String redisToken = null;
                try {
                    redisToken = (String)redisServiceImpl.opsForHashGet(RedisKeyEnum.TOKEN_VALID.getRedisKey(), RedisKeyEnum.getRedisKey(userId, clientType));
                } catch (Exception e) {
                    log.error("授权验证---- 用户[{}]获取Redis Token失效.异常[{}]", userId, ExceptionUtils.getMessage(e));
                    renderJson(response, BizExceptionEnum.ACCOUNT_OFFLINE, CommonMessage.ACCOUNT_OFFLINE);
                    return;
                }

                // 当前用户真实token与Redis中Token不一致， 则需要提示被挤下线。
                if (StringUtils.isBlank(redisToken) || !authToken.equals(redisToken)) {
                    renderJson(response, BizExceptionEnum.ACCOUNT_OFFLINE, CommonMessage.ACCOUNT_OFFLINE);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    protected abstract void renderJson(HttpServletResponse response, BizExceptionEnum bizError, CommonMessage message)
        throws ServletException, IOException;

    protected abstract boolean checkClientType(String clientType);

    protected abstract List<String> getNotAuthPath();

    protected abstract List<String> getNotClientAuthPath();
}
