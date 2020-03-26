package com.focre.adminrest.modular.auth.model.dto;

import com.focre.adminrest.modular.auth.model.entity.SysWebsiteConfig;
import lombok.Data;

/**
 * @ClassName: SysWebsiteConfigDto
 * @Description:
 * @Author wjf
 * @Date 2019/11/19
 */
@Data
public class SysWebsiteConfigDto extends SysWebsiteConfig {
    private Integer state;
}
