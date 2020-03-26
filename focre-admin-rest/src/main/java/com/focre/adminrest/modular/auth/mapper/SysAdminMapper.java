package com.focre.adminrest.modular.auth.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.focre.adminrest.modular.auth.model.dto.AdminListDto;
import com.focre.adminrest.modular.auth.model.entity.SysAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.focre.adminrest.modular.auth.model.param.AdminListParam;

import java.util.List;

/**
 * <p>
 * 系统管理员表 Mapper 接口
 * </p>
 *
 * @author ye21st
 * @since 2019-10-05
 */
public interface SysAdminMapper extends BaseMapper<SysAdmin> {

	/**
	 * @description [查询管理员分页列表]
	 * @title findPageList
	 * @author ye21st
	 * @date 2020/2/20
	 * @time 4:19 下午
	 * @param page []
	 * @param param []
	 * @return java.util.List<com.focre.adminrest.modular.auth.model.dto.AdminListDto>
	 **/
	List<AdminListDto> findPageList(Page<AdminListDto> page, AdminListParam param);

}
