package com.focre.base.entity.param;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class PageParam implements Serializable {

	private static final long serialVersionUID = -2310929878675397984L;

	@ApiModelProperty(value = "当前页(默认为1)", required = true)
	private Integer pageTo;

	@ApiModelProperty(value = "每页记录数(默认10条)", required = true)
	private Integer pageSize;

	public Integer getPageTo() {
		if (null == this.pageTo || this.pageTo < 1) {
			this.pageTo = 1;
		}
		return pageTo;
	}

	public void setPageTo(Integer pageTo) {
		this.pageTo = pageTo;
	}

	public Integer getPageSize() {
		if (null == this.pageSize || this.pageSize < 1) {
			this.pageSize = 20;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
