package com.focre.base.config;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.MessageInterpolator;
import java.util.Locale;

public class MessageInterpolatorAdapter extends ResourceBundleMessageInterpolator {

    private MessageInterpolator defaultInterpolator;

    public MessageInterpolatorAdapter(MessageInterpolator interpolator) {
        this.defaultInterpolator = interpolator;
    }

    /**
     * 将用户的 locale 信息传递给消息解释器,而不是使用默认的 JVM locale 信息
     */
    @Override
    public String interpolate(String message, Context context) {
        return defaultInterpolator.interpolate(message, context, getRequestLocale());
    }

    @Override
    public String interpolate(String message, Context context, Locale locale) {
        return defaultInterpolator.interpolate(message, context, locale);
    }

    private Locale getRequestLocale() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes) {
            return Locale.US;
        }
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String acceptLang = request.getHeader("Accept-Language");
        if (StringUtils.isBlank(acceptLang)) {
            return Locale.US;
        }
        acceptLang = acceptLang.toLowerCase();
        if (acceptLang.startsWith("zh")) {
            return Locale.SIMPLIFIED_CHINESE;
        } else if (acceptLang.startsWith("ko")) {
            return Locale.KOREA;
        } else {
            return Locale.US;
        }
    }
}
