package com.focre.adminrest.modular.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.focre.adminrest.modular.auth.model.entity.SysRole;
import com.focre.adminrest.modular.auth.model.param.AddRoleParam;
import com.focre.adminrest.modular.auth.model.param.EditRoleParam;
import com.focre.adminrest.modular.auth.model.param.RoleListParam;
import com.focre.base.entity.dto.PageDto;
import com.focre.base.entity.param.IdParam;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author ye21st
 * @since 2019-10-05
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> getRoleList(String name);

}
