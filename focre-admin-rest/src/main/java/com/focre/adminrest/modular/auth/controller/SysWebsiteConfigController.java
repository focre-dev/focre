package com.focre.adminrest.modular.auth.controller;


import com.focre.adminrest.modular.auth.model.dto.SysWebsiteConfigDto;
import com.focre.adminrest.modular.auth.model.entity.SysWebsiteConfig;
import com.focre.adminrest.modular.auth.service.SysWebsiteConfigService;
import com.focre.base.controller.BaseController;
import com.focre.base.entity.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 网站设置 前端控制器
 * </p>
 *
 * @author ye21st
 * @since 2019-11-19
 */
@RestController
@RequestMapping("/web/config")
public class SysWebsiteConfigController extends BaseController {

    @Autowired
    private SysWebsiteConfigService sysWebsiteConfigService;

    @GetMapping
    @ResponseBody
    public ResultDto getConfig(){
        SysWebsiteConfigDto dto = sysWebsiteConfigService.getConfig();
        return resultSuccess(dto);
    }

    @PostMapping(value = "/saveConfig")
    @ResponseBody
    public ResultDto saveConfig(SysWebsiteConfig config){
        sysWebsiteConfigService.saveOrUpdate(config);
        return resultSuccess();
    }
}
