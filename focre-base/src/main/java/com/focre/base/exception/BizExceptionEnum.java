package com.focre.base.exception;

/**
 * @description [All Business Exception Enum]
 * @title BizExceptionEnum
 * @author ye21st
 * @date 2019-07-12
 * @time 14:19  
 **/
public enum BizExceptionEnum implements GlobalExceptionEnum {

    /** 操作成功 */
    SUCCESS(200, "操作成功"),

    /** 操作失败 */
    FAILURE(201, "操作失败"),

    /** 签名异常 */
    SIGN_ERROR(202, "签名验证失败"),

    /** 请求限制 */
    LIMIT_REQUEST(203, "访问次数超过限制，拒绝访问"),

    /** 无权限访问 */
    NO_ACCESS(204, "无权限访问"),

    /** 无查询结果 */
    NO_RESULT(205, "无查询结果"),

    /** 请求参数不完整 */
    PARAM_ERROR(400, "请求参数错误"),

    /** Token过期 */
    TOKEN_EXPIRED(401, "Token过期"),

    /** Token异常 */
    TOKEN_ERROR(402, "Token验证失败"),

    /** 用户未登录 */
    NOT_LOGIN(403, "用户未登录"),

    /** 您的帐号已在其他地方登陆，请重新登陆 */
    ACCOUNT_OFFLINE(404, "您的帐号已在其他地方登陆，请重新登陆"),

    /** 帐号被禁用 */
    ACCOUNT_DISABLE(405, "帐号被禁用"),

    /** 客户端类型验证失败 */
    CLIENT_TYPE_ERROR(406, "客户端类型验证失败"),

    /** 服务调用异常 */
    ERROR(500, "服务调用异常"),
    ;

    BizExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static BizExceptionEnum codeOf(int code) {
        for (BizExceptionEnum item : BizExceptionEnum.values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

}
