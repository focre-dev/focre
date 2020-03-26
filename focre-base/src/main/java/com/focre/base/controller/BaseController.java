package com.focre.base.controller;

import com.focre.base.config.GlobalProperties;
import com.focre.base.entity.dto.ResultDto;
import com.focre.base.exception.BizExceptionEnum;
import com.focre.base.exception.ValidateException;
import com.focre.base.i18n.MessageService;
import com.focre.base.i18n.consts.CommonMessage;
import com.focre.base.i18n.consts.I18nMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientProperties;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: BaseController
 * @Description: BaseController
 * @Author ye21st
 * @Date 2019-07-12 10:44:05
 */
public class BaseController {

    // 日志对象
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageService messageServiceImpl;

    @Autowired
    private GlobalProperties globalProperties;

    protected String getClientType(HttpServletRequest req) {
        // 获取客户端类型
        String clientType = req.getHeader(globalProperties.getClientHeader());
        if (StringUtils.isBlank(clientType) || !StringUtils.isNumeric(clientType)) {
            throw new ValidateException(BizExceptionEnum.CLIENT_TYPE_ERROR, CommonMessage.CLIENT_TYPE_ERROR);
        }
        return clientType;
    }

    protected HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    protected HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /** 返回成功 */
    protected ResultDto resultSuccess() {
        String msg = messageServiceImpl.getMessage(CommonMessage.SUCCESS);
        return new ResultDto(msg, null);
    }

    /** 返回成功 */
    protected ResultDto resultSuccess(Object data) {
        String msg = messageServiceImpl.getMessage(CommonMessage.SUCCESS);
        return new ResultDto(msg, data);
    }

    /** 返回成功 */
    protected ResultDto resultSuccess(I18nMessage message, Object data) {
        String msg = messageServiceImpl.getMessage(message);
        return new ResultDto(msg, data);
    }

    /** 返回成功 */
    protected ResultDto resultSuccess(Object data, long total) {
        String msg = messageServiceImpl.getMessage(CommonMessage.SUCCESS);
        return new ResultDto(msg, data, total);
    }

    /** 返回失败 */
    protected ResultDto resultParamFail(I18nMessage message) {
        String msg = messageServiceImpl.getMessage(message);
        return new ResultDto(BizExceptionEnum.PARAM_ERROR, msg);
    }

    /** 返回失败 */
    protected ResultDto resultFailure() {
        String msg = messageServiceImpl.getMessage(CommonMessage.FAILURE);
        return new ResultDto(BizExceptionEnum.FAILURE, msg);
    }

    /** 返回失败 */
    protected ResultDto resultFailure(I18nMessage message) {
        String msg = messageServiceImpl.getMessage(message);
        return new ResultDto(BizExceptionEnum.FAILURE, msg);
    }

    /** 返回其他类型的失败 */
    protected ResultDto resultFailure(BizExceptionEnum exception, I18nMessage message) {
        String msg = messageServiceImpl.getMessage(message);
        return new ResultDto(exception, msg);
    }

    /** 返回其他类型的失败 */
    protected ResultDto resultFailure(BizExceptionEnum exception, I18nMessage message, Object... objects) {
        String msg = messageServiceImpl.getMessage(message, null, objects);
        return new ResultDto(exception, msg);
    }

    /** 返回其他类型的失败 */
    protected ResultDto result(BizExceptionEnum exception, Object data, I18nMessage message, Object... objects) {
        String msg = messageServiceImpl.getMessage(message, null, objects);
        return new ResultDto(exception, data, msg);
    }

}
