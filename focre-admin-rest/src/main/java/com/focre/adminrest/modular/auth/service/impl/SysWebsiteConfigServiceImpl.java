package com.focre.adminrest.modular.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.focre.adminrest.modular.auth.model.dto.SysWebsiteConfigDto;
import com.focre.adminrest.modular.auth.model.entity.SysWebsiteConfig;
import com.focre.adminrest.modular.auth.mapper.SysWebsiteConfigMapper;
import com.focre.adminrest.modular.auth.service.SysWebsiteConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站设置 服务实现类
 * </p>
 *
 * @author ye21st
 * @since 2019-11-19
 */
@Service
public class SysWebsiteConfigServiceImpl extends ServiceImpl<SysWebsiteConfigMapper, SysWebsiteConfig> implements SysWebsiteConfigService {

    @Override
    public SysWebsiteConfigDto getConfig() {
        QueryWrapper<SysWebsiteConfig> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time").last("limit 1");
        SysWebsiteConfig config = getOne(wrapper);
        SysWebsiteConfigDto dto = new SysWebsiteConfigDto();
        if(config != null){
            BeanUtils.copyProperties(config,dto);
        }
        return dto;
    }
}
