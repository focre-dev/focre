package com.focre.adminrest.modular.system.controller;


import com.focre.base.controller.BaseController;
import com.focre.base.entity.dto.ResultDto;
import com.focre.service.system.service.SysOptionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description [系统配置服务]
 * @title
 * @author ye21st
 * @date 2020/4/18
 * @time 12:02 上午
 **/
@RestController
@RequestMapping("/api/system/options")
@Api(value = "/api/system/options", description = "系统配置服务")
public class SysOptionsController extends BaseController {

	@Autowired
	private SysOptionsService sysOptionsServiceImpl;

	@PostMapping("/save")
	@ApiOperation(value = "保存配置", notes = "保存配置")
	public ResultDto save(@RequestBody Map<String, Object> optionMap){
		sysOptionsServiceImpl.save(optionMap);
		return resultSuccess();
	}

}
