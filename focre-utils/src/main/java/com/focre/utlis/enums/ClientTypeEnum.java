package com.focre.utlis.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public enum ClientTypeEnum implements BaseEnum<Integer> {

    /** 0: 全部 */
    ALL(0, "全部"),

    /** 1: APP */
    APP(1, "APP"),

    /** 2: PC */
    PC(2, "PC"),

    /** 3: 商家 */
    MERCHANT(3, "商家");

    private int code;

    private String explain;

    ClientTypeEnum(int code, String explain) {
        this.code = code;
        this.explain = explain;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getExplain() {
        return explain;
    }

    @Override
    public boolean equalsCode(Integer code) {
        if (null == code) {
            return false;
        }

        if (this.getCode() == code || code.equals(this.getCode())) {
            return true;
        }
        return false;
    }
    
    public boolean equalsCode(String code) {
        if (null == code) {
            return false;
        }

        if (code.equals(this.getCode() + "")) {
            return true;
        }
        return false;
    }

    public static ClientTypeEnum codeOf(Integer code) {
        if (null == code) {
            return null;
        }

        for (ClientTypeEnum item : ClientTypeEnum.values()) {
            if (item.getCode() == code || code.equals(item.getCode())) {
                return item;
            }
        }
        return null;
    }
    
    public static ClientTypeEnum codeOf(String code) {
        if (StringUtils.isBlank(code) || !StringUtils.isNumeric(code)) {
            return null;
        }

        for (ClientTypeEnum item : ClientTypeEnum.values()) {
            if (code.equals(item.getCode()+"")) {
                return item;
            }
        }
        return null;
    }

    public static ClientTypeEnum nameOf(String name) {
        for (ClientTypeEnum item : ClientTypeEnum.values()) {
            if (item.name().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public static Map<String, String> toNameMap(boolean isAll) {
        Map<String, String> map = new HashMap<String, String>();
        for (ClientTypeEnum item : ClientTypeEnum.values()) {
            map.put(item.name(), item.getExplain());
        }
        if (!isAll) {
            map.remove(ClientTypeEnum.ALL.name());
        }
        return map;
    }

    public static Map<Integer, String> toCodeMap(boolean isAll) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (ClientTypeEnum item : ClientTypeEnum.values()) {
            map.put(item.getCode(), item.getExplain());
        }
        if (!isAll) {
            map.remove(ClientTypeEnum.ALL.getCode());
        }
        return map;
    }
}
