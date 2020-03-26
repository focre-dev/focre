package com.focre.adminrest.modular.auth.model.param;

import com.focre.base.entity.param.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName: AdminListParam
 * @Description:
 * @Author ye21st
 * @Date 2020年02月14日17:18:42
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DeptListParam extends PageParam implements Serializable {

	private static final long serialVersionUID = -2855636800005609062L;

	@ApiModelProperty(value = "部门ID", required = false)
	private Integer deptId;

	@ApiModelProperty(value = "部门名称", required = false)
	private String name;
}
