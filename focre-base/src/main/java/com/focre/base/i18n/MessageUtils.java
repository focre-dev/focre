package com.focre.base.i18n;

import com.focre.base.i18n.consts.CommonMessage;
import com.focre.base.i18n.consts.I18nMessage;

public class MessageUtils {

	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> T getMessageByName(Class<T> messageClass, String name) {
		try {
			for (CommonMessage item : CommonMessage.values()) {
				if (item.name().equals(name)) {
					return (T) item;
				}
			}

			// 是否是国际枚举
			if (I18nMessage.class.isInstance(messageClass)) {
				for (T item : messageClass.getEnumConstants()) {
					if (((Enum<T>) item).name().equals(name)) {
						return item;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
