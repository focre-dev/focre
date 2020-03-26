package com.focre.base.jwt;

import com.focre.base.config.GlobalProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

	@Autowired
	private GlobalProperties properties;

	/**
	 * 从token中获取用户ID
	 */
	@Override
	public String getUserIdFromToken(String token) {
		return getClaimFromToken(token).getSubject();
	}

	/**
	 * 从token中获取用户ID
	 */
	@Override
	public String getUserIdFromRequest(HttpServletRequest request) {
		final String requestHeader = request.getHeader(properties.getTokenHeader());
		if (null == requestHeader || !requestHeader.startsWith(properties.getTokenPrefix())) {
			return null;
		}

		String authToken = requestHeader.substring(7);
		if (StringUtils.isBlank(authToken)) {
			return null;
		}

		return getClaimFromToken(authToken).getSubject();
	}

	/**
	 * 获取jwt发布时间
	 */
	@Override
	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token).getIssuedAt();
	}

	/**
	 * 获取jwt失效时间
	 */
	@Override
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token).getExpiration();
	}

	/**
	 * 获取jwt接收者
	 */
	@Override
	public String getAudienceFromToken(String token) {
		return getClaimFromToken(token).getAudience();
	}

	/**
	 * 获取私有的jwt claim
	 */
	@Override
	public String getPrivateClaimFromToken(String token, String key) {
		if (null == getClaimFromToken(token).get(key)) {
			return "";
		}
		return getClaimFromToken(token).get(key).toString();
	}

	/**
	 * 获取md5 key从token中
	 */
	@Override
	public String getRandomKeyFromToken(String token) {
		return getPrivateClaimFromToken(token, properties.getRandomKey());
	}

	/**
	 * 获取客户端端类型从token中
	 */
	@Override
	public String getClientTypeFromToken(String token) {
		return getPrivateClaimFromToken(token, properties.getClientHeader());
	}

	/**
	 * 获取jwt的payload部分
	 */
	@Override
	public Claims getClaimFromToken(String token) {
		return Jwts.parser().setSigningKey(properties.getSecret()).parseClaimsJws(token).getBody();
	}

	/**
	 * 解析token是否正确,不正确会报异常<br>
	 */
	@Override
	public void parseToken(String token) throws JwtException {
		Jwts.parser().setSigningKey(properties.getSecret()).parseClaimsJws(token).getBody();
	}

	/**
	 * <pre>
	 *  验证token是否失效
	 *  true:过期   false:没过期
	 * </pre>
	 */
	@Override
	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		if (null != properties.getExpiration() && properties.getExpiration() == 0) {
			if(null == expiration) {
				return false;
			}
		} else {
			if(null == expiration) {
				return true;
			}
		}
		return expiration.before(new Date());
	}

	/**
	 * 生成token(通过用户名和签名时候用的随机数)
	 */
	@Override
	public String generateToken(Integer userId, String randomKey, String clientType) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(properties.getRandomKey(), randomKey);
		if (StringUtils.isBlank(clientType)) {
			claims.put(properties.getClientHeader(), "0");
		} else {
			claims.put(properties.getClientHeader(), clientType);
		}
		return doGenerateToken(claims, userId);
	}

	/**
	 * 生成PC端伪Token
	 */
	@Override
	public String generatePcToken(String token) {
		return token + "." + getRandomKey();
	}

	/**
	 * 生成token
	 */
	private String doGenerateToken(Map<String, Object> claims, Integer subject) {
		final Date createdDate = new Date();
		if (null != properties.getExpiration() && properties.getExpiration() == 0) {
			return Jwts.builder().setClaims(claims).setSubject(subject.toString()).setIssuedAt(createdDate)
					.signWith(SignatureAlgorithm.HS512, properties.getSecret()).compact();
		} else {
			final Date expirationDate = new Date(createdDate.getTime() + properties.getExpiration() * 1000);
			return Jwts.builder().setClaims(claims).setSubject(subject.toString()).setIssuedAt(createdDate)
					.setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, properties.getSecret()).compact();
		}
	}

	/**
	 * 获取混淆MD5签名用的随机字符串
	 */
	@Override
	public String getRandomKey() {
		return RandomStringUtils.randomAlphanumeric(64);
	}
}