package com.focre.base.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PageDto
 * @Description: 分页查询返回结果
 * @author ye21st ye21st@gmail.com
 * @date 2020年02月19日15:54:56
 */
@Getter
@Setter
public class PageDto<T> implements Serializable {

    private static final long serialVersionUID = 6205651749724488939L;

    @ApiModelProperty(value = "数据列表", required = true)
    private List<T> list;

    @ApiModelProperty(value = "总数", required = true)
    private long total = 1;

    public PageDto(List<T> list, long total) {
        if (CollectionUtils.isEmpty(list)) {
            this.list = new ArrayList<T>();
            this.total = 0;
        } else {
            this.list = list;
            this.total = total;
        }

    }
}
