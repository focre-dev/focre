package com.focre.base.entity.sign;

import lombok.Data;

/**
 * @ClassName: BaseEntity
 * @Description: 接口统一接收实体对象
 * @author ye21st ye21st@gmail.com
 * @date 2020年01月31日18:30:25
 */
@Data
public class BaseSignEntity {

    /** BASE64编码的JSON字符串 */
    private String object;

    /** 签名 */
    private String sign;

    /** TS */
    private Long ts;
}
