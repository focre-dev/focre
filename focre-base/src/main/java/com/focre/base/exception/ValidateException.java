package com.focre.base.exception;

import com.focre.base.i18n.consts.I18nMessage;

/**
 * @ClassName: ValidateException
 * @Description: 数据验证错误异常封装
 * @author ye21st ye21st@gmail.com
 * @date 2020年01月31日18:27:32
 */
public class ValidateException extends GlobalException {

    private static final long serialVersionUID = -2142798283820076813L;

    public ValidateException(BizExceptionEnum bizExceptionEnum, String msg) {
        super(bizExceptionEnum.getCode(), msg);
    }

    public ValidateException(BizExceptionEnum bizExceptionEnum, I18nMessage msgKey) {
        super(bizExceptionEnum.getCode(), msgKey, new Object[] {});
    }

    public ValidateException(BizExceptionEnum bizExceptionEnum, I18nMessage msgKey, Object... objects) {
        super(bizExceptionEnum.getCode(), msgKey, objects);
    }
}
