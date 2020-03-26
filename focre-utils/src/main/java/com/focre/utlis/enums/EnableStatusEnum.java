package com.focre.utlis.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnableStatusEnum implements BaseEnum<Integer> {

    /** 0:全部 */
    ALL(0, "全部"),

    /** 1启用 */
    ENABLE(1, "启用"),

    /** 2:禁用 */
    DISABLE(2, "禁用");

    private int code;

    private String explain;

    EnableStatusEnum(int code, String explain) {
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

    public static EnableStatusEnum codeOf(Integer code) {
        if (null == code) {
            return null;
        }
        for (EnableStatusEnum item : EnableStatusEnum.values()) {
            if (item.getCode() == code || code.equals(item.getCode())) {
                return item;
            }
        }
        return null;
    }

    public static EnableStatusEnum nameOf(String name) {
        for (EnableStatusEnum item : EnableStatusEnum.values()) {
            if (item.name().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public static Map<String, String> toNameMap(boolean isAll) {
        Map<String, String> map = new HashMap<String, String>();
        for (EnableStatusEnum item : EnableStatusEnum.values()) {
            map.put(item.name(), item.getExplain());
        }
        if (!isAll) {
            map.remove(EnableStatusEnum.ALL.name());
        }
        return map;
    }

    public static Map<Integer, String> toCodeMap(boolean isAll) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (EnableStatusEnum item : EnableStatusEnum.values()) {
            map.put(item.getCode(), item.getExplain());
        }
        if (!isAll) {
            map.remove(EnableStatusEnum.ALL.getCode());
        }
        return map;
    }
}
