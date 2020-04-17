package com.focre.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.focre.service.system.mapper.SysAdminMapper;
import com.focre.service.system.model.entity.SysAdmin;
import com.focre.service.system.model.param.LoginParam;
import com.focre.service.system.service.SysAdminService;
import com.focre.base.config.GlobalProperties;
import com.focre.base.entity.dto.AuthDto;
import com.focre.base.exception.BizExceptionEnum;
import com.focre.base.exception.BusinessException;
import com.focre.base.i18n.consts.CommonMessage;
import com.focre.base.jwt.JwtTokenService;
import com.focre.base.redis.RedisKeyEnum;
import com.focre.base.redis.RedisService;
import com.focre.utlis.enums.EnableStatusEnum;
import com.focre.utlis.util.Md5Util;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统管理员表 服务实现类
 * </p>
 *
 * @author ye21st
 * @since 2019-10-05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysAdminMapper sysAdminMapper;

	@Autowired
	private JwtTokenService jwtTokenServiceImpl;

	@Autowired
	private RedisService redisServiceImpl;

	@Autowired
	private GlobalProperties properties;

	/**
	 * @param username [用户名]
	 * @return com.focre.adminrest.modular.auth.model.entity.SysAdmin
	 * @description [通过账户名获取管理员信息]
	 * @title getByName
	 * @author ye21st
	 * @date 2019/10/5
	 * @time 8:42 下午
	 **/
	@Override
	public SysAdmin getByUsername(String username) {
		QueryWrapper<SysAdmin> wrapper = new QueryWrapper<>();
		wrapper.eq("username", username);
		return sysAdminMapper.selectOne(wrapper);
	}

	/**
	 * @param param []
	 * @return com.focre.base.entity.dto.AuthDto
	 * @description [管理员登录授权操作]
	 * @title login
	 * @author ye21st
	 * @date 2019/10/5
	 * @time 8:46 下午
	 **/
	@Override
	public AuthDto login(LoginParam param, String clientType) throws Exception {
		if (param.getUsername().contains("*")){
			log.error("登录授权操作 --- 输入用户名违法,[{}]", param.getUsername());
			throw new BusinessException(BizExceptionEnum.PARAM_ERROR, CommonMessage.ACCOUNT_PASSWORD_ERROR);
		}
		SysAdmin sysAdmin = getByUsername(param.getUsername());
		if (null == sysAdmin){
			log.error("登录授权操作 --- 查询不到该用户名,[{}]", param.getUsername());
			throw new BusinessException(BizExceptionEnum.PARAM_ERROR, CommonMessage.ACCOUNT_PASSWORD_ERROR);
		}
		// 如果帐号被禁用则不能登录
		if (EnableStatusEnum.DISABLE.equalsCode(sysAdmin.getStatus())) {
			throw new BusinessException(BizExceptionEnum.FAILURE, CommonMessage.ACCOUNT_DISABLE);
		}
		// 验证密码
		String pwd = Md5Util.encrypt(param.getPassword().concat(sysAdmin.getSalt()));
		if (!pwd.equals(sysAdmin.getPassword())) {
			throw new BusinessException(BizExceptionEnum.FAILURE, CommonMessage.AUTH_ERROR);
		}
		// 获取混淆MD5签名用的随机字符串
		String randomKey = jwtTokenServiceImpl.getRandomKey();

		// 生成token
		String token = jwtTokenServiceImpl.generateToken(sysAdmin.getId(), randomKey, clientType);
		// 如果开启单设备登录，则需要将Redis中的Token进行更新
		if (CollectionUtils.isNotEmpty(properties.getSingleLoginClient())) {
			// 如果客户端类型单设备登录配置中，则需要将Redis中的Token进行更新
			if (properties.getSingleLoginClient().contains(clientType)) {
				// 将最近登录的Token保存至Redis中
				redisServiceImpl.opsForHashPut(RedisKeyEnum.TOKEN_VALID.getRedisKey(), RedisKeyEnum.getRedisKey(sysAdmin.getId(), clientType), token);
			}
		}
		return new AuthDto(token, randomKey);
	}

	public static void main(String[] args) {
		// 先生成随机字符串
		String randStr = RandomStringUtils.randomAlphanumeric(64);
		// 生成盐值
		String md5Str = Md5Util.encrypt(3, "focre");
	}
}
