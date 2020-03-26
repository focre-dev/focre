package com.focre.adminrest.modular.auth.model.param;

import com.focre.base.entity.param.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: AddAdminParam
 * @Description:
 * @Author ye21st
 * @Date 2020年02月14日17:18:42
 */
@Data
@Accessors(chain = true)
public class AddAdminParam implements Serializable {

	@NotBlank(message = "{common.not.empty.username}")
	@ApiModelProperty(value = "用户名", required = true)
	private String username;

	@ApiModelProperty(value = "姓名", required = false)
	private String name;

	@NotBlank(message = "{common.not.empty.password}")
	@ApiModelProperty(value = "密码", required = true)
	private String password;

	@NotNull(message = "{dept.not.empty.id}")
	@ApiModelProperty(value = "部门ID", required = true)
	private Integer deptId;

	@ApiModelProperty(value = "邮箱", required = false)
	private String email;

	@ApiModelProperty(value = "电话/手机号", required = false)
	private String phone;
}
