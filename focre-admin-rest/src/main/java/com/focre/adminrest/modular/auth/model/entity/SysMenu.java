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
 * 菜单表
 * </p>
 *
 * @author ye21st
 * @since 2019-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysMenu对象", description="菜单表")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单编号")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "菜单父编号")
    @TableField("pcode")
    private String pcode;

    @ApiModelProperty(value = "当前菜单的所有父菜单编号")
    @TableField("pcodes")
    private String pcodes;

    @ApiModelProperty(value = "菜单名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "备注")
    @TableField("tips")
    private String tips;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "url地址")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "菜单排序号")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "菜单层级")
    @TableField("levels")
    private Integer levels;

    @ApiModelProperty(value = "是否是菜单（1：不是  2：是）")
    @TableField("is_menu")
    private Integer isMenu;

    @ApiModelProperty(value = "菜单状态 :  1:启用   2:不启用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "是否打开(1:不打开   2:打开)")
    @TableField("is_open")
    private Integer isOpen;

    @ApiModelProperty(value = "版本(乐观锁保留字段)")
    @TableField("version")
    @Version
    private Long version;

}
