package com.focre.base.exception;

import com.focre.base.i18n.consts.I18nMessage;

/**
 * @ClassName: BusinessException
 * @Description:
 * @Author ye21st
 * @Date 2019/9/23 2:36 下午:54
 */
public class BusinessException extends GlobalException{

	private static final long serialVersionUID = 6208737701571717781L;

	public BusinessException(BizExceptionEnum bizExceptionEnum, String msg) {
		super(bizExceptionEnum.getCode(), msg);
	}

	public BusinessException(BizExceptionEnum bizExceptionEnum, I18nMessage msgKey) {
		super(bizExceptionEnum.getCode(), msgKey, new Object[] {});
	}

	public BusinessException(BizExceptionEnum bizExceptionEnum, I18nMessage msgKey, Object... objects) {
		super(bizExceptionEnum.getCode(), msgKey, objects);
	}
}
