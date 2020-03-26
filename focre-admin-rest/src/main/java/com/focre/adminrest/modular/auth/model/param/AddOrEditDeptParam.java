package com.focre.adminrest.modular.auth.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
public class AddOrEditDeptParam implements Serializable {

	private static final long serialVersionUID = -8514677621071826560L;

	@ApiModelProperty(value = "ID", required = false)
	private Integer id;

	@NotBlank(message = "{dept.not.empty.simple.name}")
	@ApiModelProperty(value = "部门简称", required = true)
	private String simpleName;

	@NotBlank(message = "{dept.not.empty.full.name}")
	@ApiModelProperty(value = "部门全称", required = true)
	private String fullName;

	@ApiModelProperty(value = "排序", required = false)
	private Integer sort;

	@NotNull(message = "{dept.not.empty.id}")
	@ApiModelProperty(value = "上级部门ID", required = true)
	private Integer parentId;
}
