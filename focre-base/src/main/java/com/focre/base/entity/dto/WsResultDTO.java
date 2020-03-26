package com.focre.base.entity.dto;

import com.focre.base.exception.BizExceptionEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WsResultDTO<T> {

	@ApiModelProperty(value = "状态 (200:成功,其他为失败)", required = true)
	private int code;

	@ApiModelProperty(value = "消息通道", required = true)
	private String channel;

	@ApiModelProperty(value = "消息(错误消息)", required = true)
	private String msg;

	@ApiModelProperty(value = "返回数据", required = true)
	private T data;
	
	@ApiModelProperty(value = "主题名称", required = true)
	private String topic;

	public WsResultDTO() {

	}

	public WsResultDTO(String channel, Integer code) {
		this.channel = channel;
		this.code = code;
	}
	
	public WsResultDTO(String channel, T data) {
		this.channel = channel;
		this.code = BizExceptionEnum.SUCCESS.getCode();
		this.data = data;
	}
	
	public WsResultDTO(String channel, T data, String topic) {
		this.channel = channel;
		this.code = BizExceptionEnum.SUCCESS.getCode();
		this.data = data;
		this.topic = topic;
	}
	
	public WsResultDTO(String channel, Integer code, String msg) {
		this.channel = channel;
		this.code = code;
		this.msg = msg;
	}

}
