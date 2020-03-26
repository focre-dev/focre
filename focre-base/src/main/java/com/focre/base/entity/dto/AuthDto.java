package com.focre.base.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Setter
@Getter
@Accessors(chain = true)
public class AuthDto implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "用于客户端混淆md5加密")
    private String randomKey;

    @ApiModelProperty(value = "错误总次数")
    private int errorTotal;

    @ApiModelProperty(value = "当前错误次数")
    private int errorCurrent;

    public AuthDto(String token, String randomKey) {
        this.token = token;
        this.randomKey = randomKey;
    }

    public AuthDto(int errorTotal, int errorCurrent) {
        this.errorTotal = errorTotal;
        this.errorCurrent = errorCurrent;
    }

}
