package com.focre.adminrest.modular.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.focre.adminrest.modular.auth.mapper.SysRelationMapper;
import com.focre.adminrest.modular.auth.model.entity.SysRelation;
import com.focre.adminrest.modular.auth.service.SysRelationService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author ye21st
 * @since 2019-10-05
 */
@Service
public class SysRelationServiceImpl extends ServiceImpl<SysRelationMapper, SysRelation> implements SysRelationService {

    @Override
    public boolean setMenu(Integer roleId,String menuIds) {
        if(StringUtils.isNotBlank(menuIds)){
            List<SysRelation> list = new ArrayList<>();
            String[] ids = menuIds.split(",|，");
            for (String id :ids){
                SysRelation relation = new SysRelation();
                relation.setRoleId(roleId);
                relation.setMenuId(Integer.parseInt(id));
                list.add(relation);
            }
            if(CollectionUtils.isNotEmpty(list)){
                if(deleteByRoleId(roleId)) {
                    return saveBatch(list);
                }
            }
        }
        return false;
    }

    private boolean deleteByRoleId(Integer roleId){
        QueryWrapper<SysRelation> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        return remove(wrapper);
    }
}
