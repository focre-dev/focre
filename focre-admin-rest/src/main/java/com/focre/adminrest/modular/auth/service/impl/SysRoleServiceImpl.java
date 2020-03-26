package com.focre.adminrest.modular.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.focre.adminrest.modular.auth.mapper.SysRoleMapper;
import com.focre.adminrest.modular.auth.model.entity.SysRole;
import com.focre.adminrest.modular.auth.service.SysDeptService;
import com.focre.adminrest.modular.auth.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ye21st
 * @since 2019-10-05
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    // 日志对象
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysDeptService sysDeptServiceImpl;

    @Override
    public List<SysRole> getRoleList(String name) {
        List<SysRole> sysRoleList = new ArrayList<>();
        if(StringUtils.isNotBlank(name)) {
            QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
            wrapper.eq("name", name);
            sysRoleList = list(wrapper);
        }else{
            sysRoleList = list();
        }
        return sysRoleList;
    }
}
