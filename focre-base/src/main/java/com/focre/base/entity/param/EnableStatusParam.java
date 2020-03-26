package com.focre.base.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ye21st
 */
@Data
public class EnableStatusParam implements Serializable {

	private static final long serialVersionUID = -605536598684236991L;

	@NotNull(message = "{common.not.empty.id}")
	@Range(min = 1, message = "{common.not.empty.id}")
	@ApiModelProperty(value = "ID", required = true)
	private Integer id;

	@NotNull(message = "{common.not.empty.id}")
	@Range(min = 1, max = 2, message = "{common.input.error.status}")
	@ApiModelProperty(value = "状态(1、启用 2、禁用)", required = true)
	private Integer status;
}
