package com.focre.base.i18n.consts;

public enum CommonMessage implements I18nMessage {

    /** 操作成功 */
    SUCCESS("common.success"),

    /** 操作失败 */
    FAILURE("common.failure"),

    /** 服务调用异常 */
    ERROR("common.error"),

    /** 请求参数错误 */
    PARAM_ERROR("common.param.error"),

    /** 帐号密码错误 */
    AUTH_ERROR("common.auth.error"),

    /** 无权限访问 */
    NO_ACCESS("common.no.access"),

    /** 无查询结果 */
    NO_RESULT("common.no.result"),

    /** 签名验证失败 */
    SIGN_ERROR("common.sign.error"),

    /** TOKEN验证失败 */
    TOKEN_ERROR("common.token.error"),

    /** TOKEN过期 */
    TOKEN_EXPIRED("common.token.expired"),

    /** 客户端类型错误 */
    CLIENT_TYPE_ERROR("common.client.type.error"),

    /** 账号密码错误 */
    ACCOUNT_PASSWORD_ERROR("common.account.password.error"),

    /** 您的帐号已被禁用，暂时无法使用 */
    ACCOUNT_DISABLE("common.account.disable"),

    /** 您的帐号已在其他地方登陆，请重新登陆 */
    ACCOUNT_OFFLINE("common.account.offline"),

    /** 文件上传失败，请勿上传空文件 */
    FILE_UPLOAD_IS_EMPTY("common.file.upload.is.empty"),

    /** 文件上传失败, 请上传小于{0}M的文件 */
    FILE_UPLOAD_MAX_FAIL("common.file.upload.max.fail"),

    /** 顶级 */
    LABEL_TOP("common.label.top")

    ;

    private String key;

    CommonMessage(String key) {
        this.key = key;
    }

    @Override
    public String getkey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
