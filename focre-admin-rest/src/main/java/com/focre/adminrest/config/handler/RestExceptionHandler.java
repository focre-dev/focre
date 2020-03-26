package com.focre.adminrest.config.handler;

import com.focre.base.exception.handler.GlobalExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @ClassName: RestExceptionHandler
 * @Description: 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 * @author ye21st ye21st@gmail.com
 * @date 2020年01月31日17:27:41
 */
@ControllerAdvice
public class RestExceptionHandler extends GlobalExceptionHandler {



}