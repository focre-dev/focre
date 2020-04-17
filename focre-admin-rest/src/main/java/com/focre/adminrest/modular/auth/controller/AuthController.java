package com.focre.adminrest.modular.auth.controller;

import com.focre.service.system.model.param.LoginParam;
import com.focre.service.system.service.SysAdminService;
import com.focre.base.controller.BaseController;
import com.focre.base.entity.dto.AuthDto;
import com.focre.base.entity.dto.ResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Api(value = "/auth",description = "鉴权服务")
public class AuthController extends BaseController {

    @Autowired
    private SysAdminService sysAdminServiceImpl;

    @PostMapping(value = "/token")
    @ApiOperation(value = "登录授权",notes = "登录授权", response = AuthDto.class)
    public ResultDto login(@Valid @RequestBody LoginParam param, HttpServletRequest req) throws Exception {
        String clientType = getClientType(req);
        AuthDto dto = sysAdminServiceImpl.login(param, clientType);
        return resultSuccess(dto);
    }
}
