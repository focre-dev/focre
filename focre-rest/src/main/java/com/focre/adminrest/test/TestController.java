package com.focre.adminrest.test;

import com.focre.base.controller.BaseController;
import com.focre.base.entity.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: TestController
 * @Description: 测试控制器
 * @Author ye21st
 * @Date 2019-07-11 18:04:30
 */
@Slf4j
@RestController
public class TestController extends BaseController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResultDto hello(@RequestParam(value = "id", required = true) Integer id) {
        String name = getNameById(id);
//        QueryWrapper<>
        return resultSuccess(name);
    }

    public String getNameById(Integer id) {
        String sql = "select username from test_user where id = ? ";
        List<String> list = jdbcTemplate.queryForList(sql, new Object[] {id}, String.class);
        return list.isEmpty() ? null : list.get(0);
    }
}
