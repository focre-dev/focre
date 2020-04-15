package com.focre.adminrest.modular.system.model.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * 系统配置表
 * </p>
 *
 * @author ye21st
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_options")
@ApiModel(value="SysOptions对象", description="系统配置表")
public class SysOptions implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "字典分组标识")
    @TableField("group_key")
    private String groupKey;

    @ApiModelProperty(value = "字典key")
    @TableField("value_key")
    private String valueKey;

    @ApiModelProperty(value = "字典值")
    @TableField("value")
    private String value;

    @ApiModelProperty(value = "版本(乐观锁保留字段)")
    @TableField("version")
    @Version
    private Long version;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

}
