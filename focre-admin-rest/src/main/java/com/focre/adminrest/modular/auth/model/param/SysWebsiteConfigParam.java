package com.focre.adminrest.modular.auth.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: SysWebsiteConfigParam
 * @Description:
 * @Author wjf
 * @Date 2019/11/20
 */
@Data
@Accessors(chain = true)
public class SysWebsiteConfigParam implements Serializable {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "网站标题", required = true)
    private String title;

    @ApiModelProperty(value = "网站地址", required = true)
    private String url;

    @ApiModelProperty(value = "网站描述", required = true)
    private String description;

    @ApiModelProperty(value = "网站关键词", required = true)
    private String keywords;

    @ApiModelProperty(value = "网站备案号", required = true)
    private String record_number;

    @ApiModelProperty(value = "版权", required = true)
    private String copyright;

    @ApiModelProperty(value = "时区", required = true)
    private Integer time_zone;

    @ApiModelProperty(value = "文件上传类型(1、图片类型,2、多媒体类型,3、文档类型,4、其他类型", required = true)
    private String file_uploud_type;

    @ApiModelProperty(value = "其他文件类型", required = true)
    private String other_file_type;
}
