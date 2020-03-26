package com.focre.adminrest.modular.auth.controller;

import com.focre.adminrest.modular.auth.model.param.LoginParam;
import com.focre.adminrest.modular.auth.service.SysAdminService;
import com.focre.base.controller.BaseController;
import com.focre.base.entity.dto.AuthDto;
import com.focre.base.entity.dto.ResultDto;
import com.focre.utlis.util.Md5Util;
import com.focre.utlis.util.StringUtil;
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

    @PostMapping(value = "/test")
    @ApiOperation(value = "登录授权",notes = "登录授权", response = AuthDto.class)
    public ResultDto test(@Valid @RequestBody LoginParam param, HttpServletRequest req) throws Exception {
        return resultSuccess();
    }

    public static void main(String[] args) {
        String salt = StringUtil.generateRandomString(64);
        String pwdStr = "111111";
        String encryptPwd = Md5Util.encrypt(2, pwdStr);
        String str = encryptPwd.concat(salt);
        String newPwd = Md5Util.encrypt(str);
        System.out.println(salt);
        System.out.println(newPwd);
    }
}
