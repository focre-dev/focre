package com.focre.adminrest.modular.auth.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: AdminResetPasswordParam
 * @Description:
 * @Author ye21st
 * @Date 2020年02月14日17:18:42
 */
@Data
@Accessors(chain = true)
public class AdminResetPasswordParam implements Serializable {

	private static final long serialVersionUID = -3225021285648111851L;

	@NotNull(message = "{common.not.empty.id}")
	@Range(min = 1, message = "{common.not.empty.id}")
	@ApiModelProperty(value = "ID", required = true)
	private Integer id;
}
