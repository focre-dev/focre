package com.focre.adminrest.modular.auth.model.entity;
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
 * 网站设置
 * </p>
 *
 * @author ye21st
 * @since 2019-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysWebsiteConfig对象", description="网站设置")
public class SysWebsiteConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "网站标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "网站地址")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "网站描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "网站关键词")
    @TableField("keywords")
    private String keywords;

    @ApiModelProperty(value = "网站备案号")
    @TableField("record_number")
    private String recordNumber;

    @ApiModelProperty(value = "版权")
    @TableField("copyright")
    private String copyright;

    @ApiModelProperty(value = "时区")
    @TableField("time_zone")
    private Integer timeZone;

    @ApiModelProperty(value = "文件上传类型(1、图片类型,2、多媒体类型,3、文档类型,4、其他类型")
    @TableField("file_uploud_type")
    private String fileUploudType;

    @ApiModelProperty(value = "其他文件类型")
    @TableField("other_file_type")
    private String otherFileType;

    @ApiModelProperty(value = "时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;

}
