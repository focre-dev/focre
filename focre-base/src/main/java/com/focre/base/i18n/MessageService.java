package com.focre.base.i18n;

import com.focre.base.i18n.consts.I18nMessage;

import java.util.Locale;

/**
 * @ClassName: IMessageService
 * @Description: 根据国际化配置文件获取文案信息
 * @author ye21st ye21st@gmail.com
 */
public interface MessageService {

	/**
	 * @Title: getMessage
	 * @author ye21st ye21st@gmail.com
	 * @date  2020年01月31日18:30:41
	 * @Description: 根据国际化配置文件中的key获取对应的文案
	 * @param key:对应/resources/i18n目录下资源文件中的key
	 * @return String
	 */
	String getMessage(I18nMessage key);
	
	String getMessage(Locale locale, I18nMessage key);

	/**
	 * @Title: getMessage
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:30:48
	 * @Description: 根据国际化配置文件中的key获取对应的文案
	 * @param key:对应/resources/i18n目录下资源文件中的key
	 * @param defaultMessage:默认文案，如果根据key找不到是生效
	 * @return String
	 */
	String getMessage(I18nMessage key, String defaultMessage);
	
	String getMessage(Locale locale, I18nMessage key, String defaultMessage);

	/**
	 * @Title: getMessage
	 * @author ye21st ye21st@gmail.com
	 * @date 2020年01月31日18:30:53
	 * @Description: 根据国际化配置文件中的key获取对应的文案
	 * @param key:对应/resources/i18n目录下资源文件中的key
	 * @param defaultMessage:默认文案，如果根据key找不到是生效
	 * @param param:用于替换文案中{0},{1}等内容(例:请输入{0}-{1}之内的整数)
	 * @return String
	 */
	String getMessage(I18nMessage key, String defaultMessage, Object... param);
	
	String getMessage(String key, String defaultMessage, Object... param);
	
	String getMessage(Locale locale, String key, String defaultMessage, Object... param);
	
	String getMessage(Locale locale, I18nMessage key, String defaultMessage, Object... param);
	
}
