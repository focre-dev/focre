package com.focre.service.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.focre.service.system.mapper.SysOptionsMapper;
import com.focre.service.system.model.entity.SysOptions;
import com.focre.service.system.service.SysOptionsService;
import com.focre.utlis.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author ye21st
 * @since 2020-04-10
 */
@Service
public class SysOptionsServiceImpl extends ServiceImpl<SysOptionsMapper, SysOptions> implements SysOptionsService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysOptionsMapper sysOptionsMapper;

	/**
	 * @param optionMap []
	 * @description [保存]
	 * @title save
	 * @author ye21st
	 * @date 2020/4/16
	 * @time 11:50 下午
	 **/
	@Override
	public void save(Map<String, Object> optionMap) {
		if (CollectionUtils.isEmpty(Collections.singleton(optionMap))) {
			return;
		}
		// 查询出列表数据
		Map<String, SysOptions> optionKeyMap = list().stream()
				.collect(Collectors.toMap(SysOptions::getValueKey, sysOptions -> sysOptions));

		List<SysOptions> optionsToCreate = new ArrayList<>();
		List<SysOptions> optionsToUpdate = new ArrayList<>();

		optionMap.forEach((key, value) -> {
			String newKey = StringUtil.underscoreName(key);
			if (newKey.contains("_")){
				newKey = newKey.substring(newKey.indexOf("_") + 1);
			}
			SysOptions oldOption = optionKeyMap.get(newKey);
			if (oldOption == null || !StringUtils.equals(oldOption.getValue(), value.toString())) {
				SysOptions options = new SysOptions();
				String groupKey = StringUtil.underscoreName(key);
				if (groupKey.contains("_")){
					groupKey = groupKey.substring(0, groupKey.indexOf("_"));
				}
				options.setGroupKey(groupKey);
				String valueKey = StringUtil.underscoreName(key);
				if (valueKey.contains("_")){
					valueKey = valueKey.substring(valueKey.indexOf("_") + 1);
				}
				options.setValueKey(valueKey);
				options.setValue(value.toString());

				if (oldOption == null){
					optionsToCreate.add(options);
				} else if (!StringUtils.equals(oldOption.getValue(), value.toString())) {
					oldOption.setValue(value.toString());
					oldOption.setVersion(oldOption.getVersion());
					optionsToUpdate.add(oldOption);
				}
			}
		});

		saveBatch(optionsToCreate, 100);

		updateBatchById(optionsToUpdate, 100);
	}
}
