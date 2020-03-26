package com.focre.base.entity.dto;

import com.focre.base.exception.BizExceptionEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description [通用结果集返回实体类]
 * @title  
 * @author ye21st
 * @date 2019-07-12
 * @time 14:32  
 **/
@Data
public class ResultDto {

    @ApiModelProperty(value = "状态 (200:成功,其他为失败)", required = true)
    private Integer code;

    @ApiModelProperty(value = "消息", required = true)
    private String msg;

    @ApiModelProperty(value = "返回数据", required = true)
    private Object data;

    @ApiModelProperty(value = "总数", required = true)
    private Long total = 1L;

    @ApiModelProperty(value = "服务器时间戳(秒)", required = true)
    private Long serverTime = System.currentTimeMillis() / 1000;

    public ResultDto() {

    }

    public ResultDto(Object data) {
        this.code = BizExceptionEnum.SUCCESS.getCode();
        this.data = data;
    }

    public ResultDto(String msg, Object data) {
        this.code = BizExceptionEnum.SUCCESS.getCode();
        this.msg = msg;
        this.data = data;
    }

    public ResultDto(String msg, Object data, long total) {
        this.code = BizExceptionEnum.SUCCESS.getCode();
        this.msg = msg;
        this.data = data;
        this.total = total;
    }

    public ResultDto(Object data, long total) {
        this.code = BizExceptionEnum.SUCCESS.getCode();
        this.data = data;
        this.total = total;
    }

    public ResultDto(BizExceptionEnum state) {
        this.code = state.getCode();
        this.msg = state.getMessage();
    }

    public ResultDto(BizExceptionEnum state, String msg) {
        this.code = state.getCode();
        this.msg = msg;
    }

    public ResultDto(BizExceptionEnum state, Object data, String msg) {
        this.code = state.getCode();
        this.msg = msg;
        this.data = data;
    }

}
