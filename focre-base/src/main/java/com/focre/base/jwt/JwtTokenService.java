package com.focre.base.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * jwt token工具类
 * </p>
 * 
 * <pre>
 *     jwt的claim里一般包含以下几种数据:
 *         1. iss -- token的发行者
 *         2. sub -- 该JWT所面向的用户
 *         3. aud -- 接收该JWT的一方
 *         4. exp -- token的失效时间
 *         5. nbf -- 在此时间段之前,不会被处理
 *         6. iat -- jwt发布时间
 *         7. jti -- jwt唯一标识,防止重复使用
 * </pre>
 * 
 * @Date 2017/8/25 10:59
 */
public interface JwtTokenService {

	/**
	 * 从token中获取用户ID
	 */
	public String getUserIdFromToken(String token);

	/**
	 * 从token中获取用户ID
	 */
	public String getUserIdFromRequest(HttpServletRequest request);

	/**
	 * 获取jwt发布时间
	 */
	public Date getIssuedAtDateFromToken(String token);

	/**
	 * 获取jwt失效时间
	 */
	public Date getExpirationDateFromToken(String token);

	/**
	 * 获取jwt接收者
	 */
	public String getAudienceFromToken(String token);

	/**
	 * 获取私有的jwt claim
	 */
	public String getPrivateClaimFromToken(String token, String randomKey);

	/**
	 * 从token中获取RandomKey
	 */
	public String getRandomKeyFromToken(String token);

	/**
	 * 从token中获取客户端端类型
	 */
	public String getClientTypeFromToken(String token);

	/**
	 * 获取jwt的payload部分
	 */
	public Claims getClaimFromToken(String token);

	/**
	 * 解析token是否正确,不正确会报异常<br>
	 */
	public void parseToken(String token) throws JwtException;

	/**
	 * <pre>
	 *  验证token是否失效
	 *  true:过期   false:没过期
	 * </pre>
	 */
	public Boolean isTokenExpired(String token);

	/**
	 * 生成token(通过用户名和签名时候用的随机数)
	 */
	public String generateToken(Integer userId, String randomKey, String clientType);

	/**
	 * 生成PC端伪Token
	 */
	public String generatePcToken(String token);

	/**
	 * 获取混淆MD5签名用的随机字符串
	 */
	public String getRandomKey();
}