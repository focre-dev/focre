package com.focre.adminrest.modular.auth.model.param;

import com.focre.base.entity.param.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName: RoleListParam
 * @Description:
 * @Author ye21st
 * @Date 2020年02月14日17:18:42
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class RoleListParam extends PageParam implements Serializable {

	private static final long serialVersionUID = -4569044340098536703L;

	@ApiModelProperty(value = "角色名称", required = false)
	private String name;
}
