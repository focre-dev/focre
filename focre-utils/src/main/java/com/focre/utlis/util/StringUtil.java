package com.focre.utlis.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

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

	public static void main(String[] args) {
		System.out.println(generateRandomString(64));
	}
}
