package com.focre.base.exception.handler;

import com.focre.base.entity.dto.ResultDto;
import com.focre.base.exception.BizExceptionEnum;
import com.focre.base.exception.BusinessException;
import com.focre.base.exception.ExceptionUtil;
import com.focre.base.exception.GlobalException;
import com.focre.base.exception.SignException;
import com.focre.base.exception.ValidateException;
import com.focre.base.i18n.MessageService;
import com.focre.base.i18n.consts.CommonMessage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 */
public class GlobalExceptionHandler {

    protected static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    protected MessageService messageServiceImpl;

    @Autowired
    protected MultipartConfigElement multipartConfig;

    /**
     * 拦截自定义全局异常
     */
    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultDto notFount(GlobalException e) {
        String msg = e.getMessage();
        if (null != e.getMsgKey()) {
            msg = messageServiceImpl.getMessage(e.getMsgKey(), "", e.getObjects());
            if (StringUtils.isBlank(msg)) {
                msg = e.getMessage();
            }
        }
        BizExceptionEnum bizExceptionEnum = BizExceptionEnum.codeOf(e.getCode());
        if (null == bizExceptionEnum) {
            bizExceptionEnum = BizExceptionEnum.FAILURE;
        }
        log.warn("全局异常异常:{}, {}, {}", msg, ExceptionUtil.getMessage(e), e.getMessage());
        return new ResultDto(bizExceptionEnum, msg);
    }

    /**
     * 拦截业务异常异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultDto businessException(BusinessException e) {
        String msg = e.getMessage();
        if (null != e.getMsgKey()) {
            msg = messageServiceImpl.getMessage(e.getMsgKey(), e.getMessage(), e.getObjects());
            if (StringUtils.isBlank(msg)) {
                msg = e.getMessage();
            }
        }

        BizExceptionEnum bizExceptionEnum = BizExceptionEnum.codeOf(e.getCode());
        if (null == bizExceptionEnum) {
            bizExceptionEnum = BizExceptionEnum.FAILURE;
        }
        log.warn("业务异常:{}, {}, {}", msg, ExceptionUtil.getMessage(e), e.getMessage());
        return new ResultDto(bizExceptionEnum, msg);
    }

    /**
     * 拦截业务验证异常异常
     */
    @ExceptionHandler(ValidateException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultDto signException(ValidateException e) {
        String msg = e.getMessage();
        if (null != e.getMsgKey()) {
            msg = messageServiceImpl.getMessage(e.getMsgKey(), e.getMessage(), e.getObjects());
            if (StringUtils.isBlank(msg)) {
                msg = e.getMessage();
            }
        }

        BizExceptionEnum bizExceptionEnum = BizExceptionEnum.codeOf(e.getCode());
        if (null == bizExceptionEnum) {
            bizExceptionEnum = BizExceptionEnum.FAILURE;
        }
        log.warn("业务验证异常:{}, {}, {}", msg, ExceptionUtil.getMessage(e), e.getMessage());
        return new ResultDto(bizExceptionEnum, msg);
    }

    /**
     * 拦截签名异常
     */
    @ExceptionHandler(SignException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultDto signException(SignException e) {
        String msg = e.getMessage();
        if (null != e.getMsgKey()) {
            msg = messageServiceImpl.getMessage(e.getMsgKey(), e.getMessage(), e.getObjects());
            if (StringUtils.isBlank(msg)) {
                msg = e.getMessage();
            }
        }

        BizExceptionEnum bizExceptionEnum = BizExceptionEnum.codeOf(e.getCode());
        if (null == bizExceptionEnum) {
            bizExceptionEnum = BizExceptionEnum.FAILURE;
        }
        log.warn("签名异常:{}, {}, {}", msg, ExceptionUtil.getMessage(e), e.getMessage());
        return new ResultDto(bizExceptionEnum, msg);
    }

    /**
     * 拦截Validation异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultDto beanValidation(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        log.debug("参数验证:{}", e.getBindingResult());
        // 默认提示信息
        String errorMessage = "";
        if (CollectionUtils.isNotEmpty(bindingResult.getFieldErrors())) {
            errorMessage = bindingResult.getFieldErrors().get(0).getDefaultMessage();
        }

        // 处理默认提示
        if (StringUtils.isBlank(errorMessage)) {
            errorMessage = messageServiceImpl.getMessage(CommonMessage.PARAM_ERROR);
        }
        return new ResultDto(BizExceptionEnum.PARAM_ERROR, errorMessage);
    }

    /**
     * 拦截Validation异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultDto beanValidation(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        log.debug("接口：{}, 参数验证:{}", e.getParameter().getMethod(), e.getBindingResult());
        // 默认提示信息
        String errorMesssage = "";
        if (CollectionUtils.isNotEmpty(bindingResult.getFieldErrors())) {
            errorMesssage = bindingResult.getFieldErrors().get(0).getDefaultMessage();
        }

        // 处理默认提示
        if (StringUtils.isBlank(errorMesssage)) {
            errorMesssage = messageServiceImpl.getMessage(CommonMessage.PARAM_ERROR);
        }
        return new ResultDto(BizExceptionEnum.PARAM_ERROR, errorMesssage);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultDto notFount(RuntimeException e, HttpServletRequest request, HttpServletResponse response) {
        String msg = messageServiceImpl.getMessage(CommonMessage.ERROR);
        log.error("运行时异常:{}, {}", msg, e);
        return new ResultDto(BizExceptionEnum.ERROR, msg);
    }

    public boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest)request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultDto handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        String msg = messageServiceImpl.getMessage(CommonMessage.FILE_UPLOAD_MAX_FAIL, null,
                DataSize.ofBytes(multipartConfig.getMaxFileSize()).toMegabytes());
        log.info("文件上传异常:{}", msg);
        return new ResultDto(BizExceptionEnum.PARAM_ERROR, msg);
    }
}
