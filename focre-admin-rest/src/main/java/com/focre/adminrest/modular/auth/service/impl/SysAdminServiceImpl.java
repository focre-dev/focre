package com.focre.adminrest.modular.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.focre.adminrest.modular.auth.mapper.SysAdminMapper;
import com.focre.adminrest.modular.auth.model.dto.AdminListDto;
import com.focre.adminrest.modular.auth.model.entity.SysAdmin;
import com.focre.adminrest.modular.auth.model.entity.SysDept;
import com.focre.adminrest.modular.auth.model.param.AddAdminParam;
import com.focre.adminrest.modular.auth.model.param.AdminListParam;
import com.focre.adminrest.modular.auth.model.param.EditAdminParam;
import com.focre.adminrest.modular.auth.model.param.LoginParam;
import com.focre.adminrest.modular.auth.service.SysAdminService;
import com.focre.adminrest.modular.auth.service.SysDeptService;
import com.focre.base.config.GlobalProperties;
import com.focre.base.entity.dto.AuthDto;
import com.focre.base.entity.dto.PageDto;
import com.focre.base.entity.param.EnableStatusParam;
import com.focre.base.entity.param.IdParam;
import com.focre.base.exception.BizExceptionEnum;
import com.focre.base.exception.BusinessException;
import com.focre.base.i18n.consts.CommonMessage;
import com.focre.base.i18n.consts.RestMessage;
import com.focre.base.jwt.JwtTokenService;
import com.focre.base.redis.RedisKeyEnum;
import com.focre.base.redis.RedisService;
import com.focre.base.util.BeanUtil;
import com.focre.utlis.enums.EnableStatusEnum;
import com.focre.utlis.util.Md5Util;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

	private static final String ADMIN_USER = RedisKeyEnum.TOKEN_VALID.getRedisKey();

	@Autowired
	private SysAdminMapper sysAdminMapper;

	@Autowired
	private JwtTokenService jwtTokenServiceImpl;

	@Autowired
	private RedisService redisServiceImpl;

	@Autowired
	private SysDeptService sysDeptServiceImpl;

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

	/**
	 * @param param []
	 * @return java.util.List<com.focre.adminrest.modular.auth.model.entity.SysAdmin>
	 * @description [获取管理员分页列表]
	 * @title getAdminList
	 * @author ye21st
	 * @date 2020/2/19
	 * @time 2:12 下午
	 **/
	@Override
	public PageDto<AdminListDto> findPageList(AdminListParam param) throws Exception{
		long total = 0L;
		Page<AdminListDto> page = new Page<AdminListDto>(param.getPageTo(), param.getPageSize());
		List<AdminListDto> list = sysAdminMapper.findPageList(page, param);
		List<Integer> deptIds = new ArrayList<>();
		for (AdminListDto listDto : list){
			if (!deptIds.contains(listDto.getDeptId())){
				deptIds.add(listDto.getDeptId());
			}
		}
		if (CollectionUtils.isNotEmpty(deptIds)){
			List<SysDept> deptList = sysDeptServiceImpl.listByIds(deptIds);
			for (AdminListDto dto : list){
				for (SysDept sysDept : deptList){
					if (dto.getDeptId().equals(sysDept.getId())){
						dto.setDept(sysDept.getFullName());
					}
				}
			}
		}
		if (CollectionUtils.isEmpty(list)) {
			list = new ArrayList<>();
		}
		total = page.getTotal();
		return new PageDto<AdminListDto>(list, total);
	}

	/**
	 * @param param []
	 * @return AdminListDto
	 * @description [根据ID查询管理员信息]
	 * @title getById
	 * @author ye21st
	 * @date 2020/2/23
	 * @time 6:37 下午
	 **/
	@Override
	public AdminListDto findById(IdParam param) throws Exception {
		SysAdmin sysAdmin = getById(param.getId());
		if (null == sysAdmin){
			throw new BusinessException(BizExceptionEnum.NO_RESULT, CommonMessage.NO_RESULT);
		}
		return BeanUtil.copyProperties(sysAdmin, AdminListDto.class);
	}

	/**
	 * @param param []
	 * @description [添加管理员]
	 * @title addAdmin
	 * @author ye21st
	 * @date 2020/2/26
	 * @time 5:36 下午
	 **/
	@Override
	public void addAdmin(AddAdminParam param) throws Exception {

	}

	/**
	 * @param param []
	 * @description [编辑管理员]
	 * @title editAdmin
	 * @author ye21st
	 * @date 2020/2/28
	 * @time 1:35 下午
	 **/
	@Override
	public void editAdmin(EditAdminParam param) throws Exception {
		SysAdmin sysAdmin = BeanUtil.copyProperties(param, SysAdmin.class);
		SysAdmin nowInfo = getById(param.getId());
		if (null == nowInfo){
			log.error("更新管理员信息 --- 查询管理员[{}]信息失败", param.getId());
			throw new BusinessException(BizExceptionEnum.FAILURE, CommonMessage.NO_RESULT);
		}
		sysAdmin.setVersion(nowInfo.getVersion());
		boolean isUpdate = updateById(sysAdmin);
		if (!isUpdate){
			log.error("更新管理员信息 --- 更新管理员[{}]信息失败", param.getId());
			throw new BusinessException(BizExceptionEnum.FAILURE, RestMessage.ADMIN_UPDATE_FAILURE);
		}
	}

	/**
	 * @param param []
	 * @description [删除管理员]
	 * @title removeAdmin
	 * @author ye21st
	 * @date 2020/2/28
	 * @time 4:28 下午
	 **/
	@Override
	public void deleteAdmin(IdParam param) throws Exception {
		boolean isDelete = removeById(param.getId());
		if(!isDelete){
			log.error("删除管理员操作 --- 删除管理员[{}]失败!", param.getId());
			throw new BusinessException(BizExceptionEnum.FAILURE, RestMessage.ADMIN_DELETE_FAILURE);
		}
	}

	/**
	 * @param param []
	 * @description [设置管理员状态]
	 * @title setStatus
	 * @author ye21st
	 * @date 2020/2/28
	 * @time 4:59 下午
	 **/
	@Override
	public void setStatus(EnableStatusParam param) throws Exception {
		SysAdmin sysAdmin = BeanUtil.copyProperties(param, SysAdmin.class);
		SysAdmin nowInfo = getById(param.getId());
		if (null == nowInfo){
			log.error("更新管理员状态操作 --- 查询管理员[{}]信息失败", param.getId());
			throw new BusinessException(BizExceptionEnum.FAILURE, CommonMessage.NO_RESULT);
		}
		sysAdmin.setVersion(nowInfo.getVersion());
		boolean isUpdate = updateById(sysAdmin);
		if (!isUpdate){
			log.error("更新管理员状态操作 --- 更新管理员状态[{}]失败!", param.getId());
			throw new BusinessException(BizExceptionEnum.FAILURE, RestMessage.ADMIN_UPDATE_STATUS_FAILURE);
		}
	}

	public static void main(String[] args) {
		// 先生成随机字符串
		String randStr = RandomStringUtils.randomAlphanumeric(64);
		// 生成盐值
		String md5Str = Md5Util.encrypt(3, "focre");
	}
}
