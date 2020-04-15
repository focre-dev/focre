package com.focre.adminrest.modular.system.controller;


import com.focre.base.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 系统管理员表 前端控制器
 * </p>
 *
 * @author ye21st
 * @since 2019-11-19
 */
@Controller
@RequestMapping("/auth/admin")
@Api(value = "/auth/admin", description = "管理员服务")
public class SysAdminController extends BaseController {

}
