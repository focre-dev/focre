package com.focre.adminrest.modular.auth.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName: LoginParam
 * @Description:
 * @Author ye21st
 * @Date 2019/9/29 11:41 上午:21
 */
@Data
@Accessors(chain = true)
public class LoginParam implements Serializable {

	@NotBlank(message = "{common.not.empty.username}")
	@ApiModelProperty(value = "帐号", required = true)
	private String username;

	@NotBlank(message = "{common.not.empty.login.password}")
	@ApiModelProperty(value = "密码", required = true)
	private String password;

}
