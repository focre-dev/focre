package com.focre.adminrest.modular.auth.controller;


import com.focre.adminrest.modular.auth.model.dto.AdminListDto;
import com.focre.adminrest.modular.auth.model.param.AddAdminParam;
import com.focre.adminrest.modular.auth.model.param.AdminListParam;
import com.focre.adminrest.modular.auth.model.param.EditAdminParam;
import com.focre.adminrest.modular.auth.service.SysAdminService;
import com.focre.base.controller.BaseController;
import com.focre.base.entity.dto.PageDto;
import com.focre.base.entity.dto.ResultDto;
import com.focre.base.entity.param.EnableStatusParam;
import com.focre.base.entity.param.IdParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;

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

    @Autowired
    private SysAdminService sysAdminServiceImpl;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取管理员列表", notes = "获取管理员列表", response = AdminListDto.class)
    public ResultDto list(@Valid AdminListParam param) throws Exception{
        PageDto<AdminListDto> result = sysAdminServiceImpl.findPageList(param);
        if (null == result || CollectionUtils.isEmpty(result.getList())) {
            return resultSuccess(new ArrayList<AdminListDto>());
        }
        return resultSuccess(result.getList(), result.getTotal());
    }

    @ResponseBody
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取管理员信息", notes = "获取管理员信息", response = AdminListDto.class)
    public ResultDto detail(@Valid IdParam param) throws Exception{
        AdminListDto dto = sysAdminServiceImpl.findById(param);
        return resultSuccess(dto);
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "添加管理员", notes = "添加管理员")
    public ResultDto add(@RequestBody @Valid AddAdminParam param) throws Exception {
        sysAdminServiceImpl.addAdmin(param);
        return resultSuccess();
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "修改管理员信息", notes = "修改管理员信息")
    public ResultDto edit(@RequestBody @Valid EditAdminParam param) throws Exception{
        sysAdminServiceImpl.editAdmin(param);
        return resultSuccess();
    }

    @ResponseBody
    @DeleteMapping
    @ApiOperation(value = "删除管理员", notes = "删除管理员")
    public ResultDto delete(@Valid IdParam param) throws Exception{
        sysAdminServiceImpl.deleteAdmin(param);
        return resultSuccess();
    }

    @ResponseBody
    @PutMapping(value = "/status")
    @ApiOperation(value = "修改管理员状态(启用或禁用)", notes = "修改管理员状态(启用或禁用)")
    public ResultDto setStatus(@RequestBody @Valid EnableStatusParam param) throws Exception{
        sysAdminServiceImpl.setStatus(param);
        return resultSuccess();
    }
}
