package com.focre.base.exception;

import com.focre.base.i18n.consts.I18nMessage;

/**
 * @ClassName: SignException
 * @Description: 签名异常封装
 * @author ye21st ye21st@gmail.com
 * @date 2020年01月31日18:25:18
 */
public class SignException extends GlobalException {

    private static final long serialVersionUID = 1875008139009854453L;

    public SignException(BizExceptionEnum bizExceptionEnum, String msg) {
        super(bizExceptionEnum.getCode(), msg);
    }

    public SignException(BizExceptionEnum bizExceptionEnum, I18nMessage msgKey) {
        super(bizExceptionEnum.getCode(), msgKey, new Object[] {});
    }

    public SignException(BizExceptionEnum bizExceptionEnum, I18nMessage msgKey, Object... objects) {
        super(bizExceptionEnum.getCode(), msgKey, objects);
    }
}
