package com.focre.base.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ye21st
 */
@Data
public class IdParam implements Serializable {

	private static final long serialVersionUID = 4692834418117936057L;

	@NotNull(message = "{common.not.empty.id}")
	@Range(min = 1, message = "{common.not.empty.id}")
	@ApiModelProperty(value = "ID", required = true)
	private Integer id;
}
