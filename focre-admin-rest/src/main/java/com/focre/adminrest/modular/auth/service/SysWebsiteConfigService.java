package com.focre.adminrest.modular.auth.service;

import com.focre.adminrest.modular.auth.model.dto.SysWebsiteConfigDto;
import com.focre.adminrest.modular.auth.model.entity.SysWebsiteConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.focre.adminrest.modular.auth.model.param.SysWebsiteConfigParam;

/**
 * <p>
 * 网站设置 服务类
 * </p>
 *
 * @author ye21st
 * @since 2019-11-19
 */
public interface SysWebsiteConfigService extends IService<SysWebsiteConfig> {
    SysWebsiteConfigDto getConfig();
}
