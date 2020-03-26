package com.focre.base.i18n.impl;

import com.focre.base.i18n.MessageService;
import com.focre.base.i18n.consts.CommonMessage;
import com.focre.base.i18n.consts.I18nMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService {

    private Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(I18nMessage message) {
        return getMessage(message, null, new Object[] {});
    }

    @Override
    public String getMessage(Locale locale, I18nMessage message) {
        return getMessage(message, null, new Object[] {});
    }

    @Override
    public String getMessage(I18nMessage message, String defaultMessage) {
        return getMessage(message, defaultMessage, new Object[] {});
    }

    @Override
    public String getMessage(Locale locale, I18nMessage message, String defaultMessage) {
        return getMessage(locale, message, defaultMessage, new Object[] {});
    }

    @Override
    public String getMessage(I18nMessage message, String defaultMessage, Object... param) {
        // 获取语言环境
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(locale, message, defaultMessage, param);
    }

    @Override
    public String getMessage(Locale locale, I18nMessage message, String defaultMessage, Object... param) {
        if (null == message) {
            log.warn("获取国际化资源异常 msgKey为空");
            if (StringUtils.isNotBlank(defaultMessage)) {
                return defaultMessage;
            }
            return messageSource.getMessage(CommonMessage.FAILURE.getkey().trim(), param, locale);
        }

        return getMessage(locale, message.getkey(), defaultMessage, param);
    }

    @Override
    public String getMessage(String key, String defaultMessage, Object... param) {
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(locale, key, defaultMessage, param);
    }

    @Override
    public String getMessage(Locale locale, String key, String defaultMessage, Object... param) {
        // 设置信息
        String msg = "";
        if (null == key) {
            log.warn("获取国际化资源异常 msgKey为空");
            if (StringUtils.isNotBlank(defaultMessage)) {
                return defaultMessage;
            }
            return messageSource.getMessage(CommonMessage.FAILURE.getkey().trim(), param, locale);
        }

        try {

            // 判断是否有默认提示
            if (StringUtils.isBlank(defaultMessage)) {
                msg = messageSource.getMessage(key.trim(), param, locale);
            } else {
                msg = messageSource.getMessage(key.trim(), param, defaultMessage, locale);
            }

            // 如果配置为空， 且默认信息不为空
            if (StringUtils.isBlank(msg) && StringUtils.isNotBlank(msg)) {
                msg = defaultMessage;
            }
        } catch (NoSuchMessageException e) {
            log.warn("获取国际化资源异常  message:{}, 异常信息:{}", key, e.getMessage());
            if (StringUtils.isNotBlank(defaultMessage)) {
                msg = defaultMessage;
            }
        } catch (Exception e) {
            log.warn("获取国际化资源异常：{}", e);
            if (StringUtils.isNotBlank(defaultMessage)) {
                msg = defaultMessage;
            }
        }

        // 设置默认值
        if (StringUtils.isBlank(msg)) {
            msg = key;
        }

        return msg;
    }

}
