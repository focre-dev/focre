package com.focre.base.exception;

import org.apache.commons.lang3.StringUtils;

public class ExceptionUtil {

    public static String getMessage(Exception e) {
        if (null == e) {
            return "";
        }

        if (StringUtils.isNotBlank(e.getMessage())) {
            return e.getMessage();
        }

        if (null != e.getCause()) {
            if (StringUtils.isNotBlank(e.getCause().getMessage())) {
                return e.getCause().getMessage();
            } else {
                return e.getCause().toString();
            }
        }

        return e.toString();
    }
}
