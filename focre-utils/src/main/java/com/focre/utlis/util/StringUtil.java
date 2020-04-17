package com.focre.utlis.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: StringUtil
 * @Description: String Util
 * @Author ye21st
 * @Date 2019/11/7 2:13 下午:34
 */
public class StringUtil {

	public static String generateRandomString(Integer count){
		return RandomStringUtils.randomAlphanumeric(count);
	}

	/**
	 * @description [将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。]
	 * @title underscoreName
	 * @author ye21st
	 * @date 2020/4/17
	 * @time 11:20 下午
	 * @param name [转换前的驼峰式命名的字符串]
	 * @return java.lang.String 转换后下划线大写方式命名的字符串
	 **/
	public static String underscoreName(String name){
		StringBuilder result = new StringBuilder();
		if (StringUtils.isNotEmpty(name)){
			result.append(name.substring(0, 1).toUpperCase());
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
					result.append("_");
				}
				result.append(s.toUpperCase());
			}
		}
		return result.toString();
	}

	/**
	 * @description [将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。]
	 * @title camelName
	 * @author ye21st
	 * @date 2020/4/17
	 * @time 11:26 下午
	 * @param name [转换前的下划线大写方式命名的字符串]
	 * @return java.lang.String 转换后的驼峰式命名的字符串
	 **/
	public static String camelName(String name) {
		StringBuilder result = new StringBuilder();
		if (StringUtils.isEmpty(name)){
			return "";
		} else if (!name.contains("_")){
			return name.substring(0, 1).toLowerCase() + name.substring(1);
		}
		String[] camels = name.split("_");
		for (String camel : camels){
			if (StringUtils.isEmpty(camel)){
				continue;
			}
			if (result.length() == 0){
				result.append(camel.toLowerCase());
			} else {
				result.append(camel.substring(0, 1).toUpperCase());
				result.append(camel.substring(1).toLowerCase());
			}
		}
		return result.toString();
	}

}
