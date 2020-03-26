package com.focre.adminrest.modular.auth.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: AddOrEditRoleParam
 * @Description:
 * @Author ye21st
 * @Date 2020年02月14日17:18:42
 */
@Data
@Accessors(chain = true)
public class AddRoleParam implements Serializable {

	private static final long serialVersionUID = -7396708247877945963L;

	@NotBlank(message = "{role.not.empty.name}")
	@ApiModelProperty(value = "角色名称", required = true)
	private String name;

	@NotBlank(message = "{role.not.empty.code}")
	@ApiModelProperty(value = "角色编码(唯一)", required = true)
	private String code;

	@NotNull(message = "{role.not.empty.parent.id}")
	@ApiModelProperty(value = "父角色ID", required = true)
	private Integer parentId;

	@NotNull(message = "{dept.not.empty.id}")
	@ApiModelProperty(value = "部门ID", required = true)
	private Integer deptId;

	@ApiModelProperty(value = "序号", required = false)
	private Integer sort;
}
