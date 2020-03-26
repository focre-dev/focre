package com.focre.adminrest.service.impl;

import com.focre.adminrest.modular.auth.service.SysAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtUserDetailsServiceImpl {

    @Autowired
    private SysAdminService sysAdminServiceImpl;
}
