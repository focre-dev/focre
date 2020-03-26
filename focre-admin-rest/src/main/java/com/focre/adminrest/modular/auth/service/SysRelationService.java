package com.focre.adminrest.modular.auth.service;

import com.focre.adminrest.modular.auth.model.entity.SysRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 *
 * @author ye21st
 * @since 2019-10-05
 */
public interface SysRelationService extends IService<SysRelation> {

    boolean setMenu(Integer roleId,String menuIds);
}
