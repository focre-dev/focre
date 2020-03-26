package com.focre.adminrest.modular.auth.model.dto;

import com.focre.adminrest.modular.auth.model.entity.SysAdmin;
import com.focre.adminrest.modular.auth.model.entity.SysRole;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: SysUserDto
 * @Description:
 * @Author ye21st
 * @Date 2019/9/29 12:04 下午:15
 */
@Data
public class SysAdminRoleDto extends SysAdmin {

	private Integer state;

	private List<SysRole> sysRoles;

}
