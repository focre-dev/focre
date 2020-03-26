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
public class EditRoleParam implements Serializable {

	private static final long serialVersionUID = -3294237163525236007L;

	@ApiModelProperty(value = "ID", required = true)
	private Integer id;

	@NotBlank(message = "{role.not.empty.name}")
	@ApiModelProperty(value = "角色名称", required = true)
	private String name;

	@NotNull(message = "{role.not.empty.parent.id}")
	@ApiModelProperty(value = "父角色ID", required = true)
	private Integer parentId;

	@NotNull(message = "{dept.not.empty.id}")
	@ApiModelProperty(value = "部门ID", required = true)
	private Integer deptId;

	@ApiModelProperty(value = "序号", required = false)
	private Integer sort;
}
