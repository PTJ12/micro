package xpit.top.enums;

public enum HttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    // 登录
    LOGIN_ERROR(401, "用户名或密码错误"),
    NO_LOGIN(402, "用户未登录"),
    NULL_POINTER(101, "空指针异常"),

    NO_OPERATOR_AUTH(403, "无权限操作"),
    SYSTEM_ERROR(500, "出现错误"),
    USERNAME_EXIST(501, "用户名已存在"),
    PHONE_NUMBER_EXIST(502, "手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    SQL_Integrity(500,"该数据有关联数据，操作失败"),
    SQL_ERROR(500, "数据库异常，操作失败"),
    JWT_EXPIRED(500, "登录过期"),
    CONTEXT_NOT_NULL(506, "内容不能为空");

    int code;
    String msg;

    HttpCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
