package com.focre.service.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.focre.service.system.model.entity.SysOptions;

import java.util.Map;

/**
 * <p>
 * 系统配置表 服务类
 * </p>
 *
 * @author ye21st
 * @since 2020-04-10
 */
public interface SysOptionsService extends IService<SysOptions> {

	/**
	 * @description [保存]
	 * @title save
	 * @author ye21st
	 * @date 2020/4/16
	 * @time 11:50 下午
	 * @param optionMap []
	 **/
	void save(Map<String, Object> optionMap);

}
