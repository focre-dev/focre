package com.focre.base.exception;

import com.focre.base.i18n.consts.I18nMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 7692495539282621634L;

    // 友好提示的code码
    protected int code;

    // 友好提示
    protected String message;

    // 过国际化提示key
    protected I18nMessage msgKey;

    // 过国际化提示value
    protected Object[] objects;

    protected GlobalException(int code, String message) {
        this.setValues(code, message);
    }

    public GlobalException(GlobalExceptionEnum bizExceptionEnum) {
        this.setValues(bizExceptionEnum.getCode(), bizExceptionEnum.getMessage());
    }

    public GlobalException(GlobalExceptionEnum bizExceptionEnum, String message) {
        this.setValues(bizExceptionEnum.getCode(), message);
    }

    public GlobalException(GlobalExceptionEnum bizExceptionEnum, I18nMessage msgKey) {
        this.code = bizExceptionEnum.getCode();
        this.msgKey = msgKey;
    }

    public GlobalException(GlobalExceptionEnum bizExceptionEnum, I18nMessage msgKey, Object[] objects) {
        this.code = bizExceptionEnum.getCode();
        this.msgKey = msgKey;
        this.objects = objects;
    }

    public GlobalException(int code, I18nMessage msgKey, Object[] objects) {
        this.code = code;
        this.msgKey = msgKey;
        this.objects = objects;
    }

    private void setValues(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
