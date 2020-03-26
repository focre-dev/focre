package com.focre.adminrest.modular.auth.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: AdminListDto
 * @Description: AdminListDto
 * @Author ye21st
 * @Date 2020/2/19 3:55 下午:18
 */
@Data
public class AdminListDto implements Serializable {

	private static final long serialVersionUID = 6833755732997240984L;

	@ApiModelProperty(value = "ID")
	private Integer id;

	@ApiModelProperty(value = "账号")
	private String username;

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "角色")
	private String roleIds;

	@ApiModelProperty(value = "部门ID")
	private Integer deptId;

	@ApiModelProperty(value = "部门")
	private String dept;

	@ApiModelProperty(value = "状态")
	private Integer status;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "电话")
	private String phone;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
}
