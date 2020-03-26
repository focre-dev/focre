package com.focre.utlis.enums;

/**
 * @description [枚举父类]
 * @title
 * @author ye21st ye21st@gamil.com
 * @date 2020/3/26
 * @time 10:35 上午
 **/
public interface BaseEnum<T> {

    boolean equalsCode(Integer code);

    int getCode();

    String getExplain();
}
