package com.focre.adminrest.modular.auth.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: EditAdminParam
 * @Description:
 * @Author ye21st
 * @Date 2020年02月14日17:18:42
 */
@Data
@Accessors(chain = true)
public class EditAdminParam implements Serializable {

	@NotNull(message = "{common.not.empty.id}")
	@Range(min = 1, message = "{common.not.empty.id}")
	@ApiModelProperty(value = "ID", required = true)
	private Integer id;

	@ApiModelProperty(value = "姓名", required = false)
	private String name;

	@NotNull(message = "{dept.not.empty.id}")
	@ApiModelProperty(value = "部门ID", required = true)
	private Integer deptId;

	@ApiModelProperty(value = "邮箱", required = false)
	private String email;

	@ApiModelProperty(value = "电话/手机号", required = false)
	private String phone;
}
