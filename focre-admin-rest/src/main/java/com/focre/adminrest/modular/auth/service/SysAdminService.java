package com.focre.adminrest.modular.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.focre.adminrest.modular.auth.model.dto.AdminListDto;
import com.focre.adminrest.modular.auth.model.dto.SysAdminRoleDto;
import com.focre.adminrest.modular.auth.model.entity.SysAdmin;
import com.focre.adminrest.modular.auth.model.param.AddAdminParam;
import com.focre.adminrest.modular.auth.model.param.AdminListParam;
import com.focre.adminrest.modular.auth.model.param.AdminResetPasswordParam;
import com.focre.adminrest.modular.auth.model.param.EditAdminParam;
import com.focre.adminrest.modular.auth.model.param.LoginParam;
import com.focre.base.entity.dto.AuthDto;
import com.focre.base.entity.dto.PageDto;
import com.focre.base.entity.param.EnableStatusParam;
import com.focre.base.entity.param.IdParam;

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

	/**
	 * @description [获取管理员分页列表]
	 * @title getAdminList 
	 * @author ye21st
	 * @date 2020/2/19
	 * @time 2:12 下午
	 * @param param []
	 * @return java.util.List<com.focre.adminrest.modular.auth.model.entity.SysAdmin>
	 **/
	PageDto<AdminListDto> findPageList(AdminListParam param) throws Exception;

	/**
	 * @description [根据ID查询管理员信息]
	 * @title getById
	 * @author ye21st
	 * @date 2020/2/23
	 * @time 6:37 下午
	 * @param param []
	 * @return AdminListDto
	 **/
	AdminListDto findById(IdParam param) throws Exception;

	/**
	 * @description [添加管理员]
	 * @title addAdmin
	 * @author ye21st
	 * @date 2020/2/26
	 * @time 5:36 下午
	 * @param param []
	 **/
	void addAdmin(AddAdminParam param) throws Exception;

	/**
	 * @description [编辑管理员]
	 * @title editAdmin
	 * @author ye21st
	 * @date 2020/2/28
	 * @time 1:35 下午
	 * @param param []
	 **/
	void editAdmin(EditAdminParam param) throws Exception;

	/**
	 * @description [删除管理员]
	 * @title removeAdmin
	 * @author ye21st
	 * @date 2020/2/28
	 * @time 4:28 下午
	 * @param param []
	 **/
	void deleteAdmin(IdParam param) throws Exception;

	/**
	 * @description [设置管理员状态]
	 * @title setStatus
	 * @author ye21st
	 * @date 2020/2/28
	 * @time 4:59 下午
	 * @param param []
	 **/
	void setStatus(EnableStatusParam param) throws Exception;

}
