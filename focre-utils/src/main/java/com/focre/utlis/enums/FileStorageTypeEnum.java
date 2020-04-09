package com.focre.utlis.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @description [文件存储类型枚举]
 * @title
 * @author ye21st
 * @date 2020/4/9
 * @time 3:37 下午
 **/
public enum FileStorageTypeEnum implements BaseEnum<Integer> {

    /** 0: 全部 */
    ALL(0, "全部"),

    /** 1: LOCAL */
    LOCAL(1, "LOCAL"),

    /** 2: ALIYUN */
    ALIYUN(2, "ALIYUN"),

    /** 3: TENCENT */
    TENCENT(3, "TENCENT"),

    /** 4: QINIU */
    QINIU(4, "QINIU");

    private int code;

    private String explain;

    FileStorageTypeEnum(int code, String explain) {
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

    public static FileStorageTypeEnum codeOf(Integer code) {
        if (null == code) {
            return null;
        }

        for (FileStorageTypeEnum item : FileStorageTypeEnum.values()) {
            if (item.getCode() == code || code.equals(item.getCode())) {
                return item;
            }
        }
        return null;
    }
    
    public static FileStorageTypeEnum codeOf(String code) {
        if (StringUtils.isBlank(code) || !StringUtils.isNumeric(code)) {
            return null;
        }

        for (FileStorageTypeEnum item : FileStorageTypeEnum.values()) {
            if (code.equals(item.getCode()+"")) {
                return item;
            }
        }
        return null;
    }

    public static FileStorageTypeEnum nameOf(String name) {
        for (FileStorageTypeEnum item : FileStorageTypeEnum.values()) {
            if (item.name().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public static Map<String, String> toNameMap(boolean isAll) {
        Map<String, String> map = new HashMap<String, String>();
        for (FileStorageTypeEnum item : FileStorageTypeEnum.values()) {
            map.put(item.name(), item.getExplain());
        }
        if (!isAll) {
            map.remove(FileStorageTypeEnum.ALL.name());
        }
        return map;
    }

    public static Map<Integer, String> toCodeMap(boolean isAll) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (FileStorageTypeEnum item : FileStorageTypeEnum.values()) {
            map.put(item.getCode(), item.getExplain());
        }
        if (!isAll) {
            map.remove(FileStorageTypeEnum.ALL.getCode());
        }
        return map;
    }
}
