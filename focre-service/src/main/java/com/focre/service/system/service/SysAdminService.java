package com.focre.service.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.focre.service.system.model.entity.SysAdmin;
import com.focre.service.system.model.param.LoginParam;
import com.focre.base.entity.dto.AuthDto;

/**
 * <p>
 * 系统管理员表 服务类
 * </p>
 *
 * @author ye21st
 * @since 2019-10-05
 */
public interface SysAdminService extends IService<SysAdmin> {

	/**
	 * @description [通过账户名获取管理员信息]
	 * @title getByUsername
	 * @author ye21st
	 * @date 2019/10/5
	 * @time 8:42 下午
	 * @param username [用户名]
	 * @return com.focre.adminrest.modular.auth.model.entity.SysAdmin
	 **/
	SysAdmin getByUsername(String username);

	/**
	 * @description [管理员登录授权操作]
	 * @title login
	 * @author ye21st
	 * @date 2019/10/5
	 * @time 8:46 下午
	 * @param param []
	 * @return com.focre.base.entity.dto.AuthDto
	 **/
	AuthDto login(LoginParam param, String clientType) throws Exception;

}
