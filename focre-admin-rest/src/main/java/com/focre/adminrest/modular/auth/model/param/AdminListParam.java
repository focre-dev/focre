package com.focre.adminrest.modular.auth.model.param;

import com.focre.base.entity.param.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: AdminListParam
 * @Description:
 * @Author ye21st
 * @Date 2020年02月14日17:18:42
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AdminListParam extends PageParam implements Serializable {

	@ApiModelProperty(value = "部门ID", required = false)
	private Integer deptId;

	@ApiModelProperty(value = "账号/姓名", required = false)
	private String keyword;

	@ApiModelProperty(value = "创建时间", required = false)
	private String createTime;
}
