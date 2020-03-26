package com.focre.adminrest.modular.auth.model.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * <p>
 * 部门表
 * </p>
 *
 * @author ye21st
 * @since 2019-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysDept对象", description="部门表")
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "父部门id")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "父级ids")
    @TableField("parent_ids")
    private String parentIds;

    @ApiModelProperty(value = "标识")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "简称")
    @TableField("simple_name")
    private String simpleName;

    @ApiModelProperty(value = "全称")
    @TableField("full_name")
    private String fullName;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "版本(乐观锁保留字段)")
    @TableField("version")
    @Version
    private Long version;

}
