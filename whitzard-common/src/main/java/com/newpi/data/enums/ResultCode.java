package com.newpi.data.enums;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/23 10:32 AM
 * @desc:
 */
public enum ResultCode {
    SUCCESS(200, "成功"),

    ERROR(500, "错误"),
    ARGS_ERROR(5001, "参数错误"),
    SERVER_ERROR(5002, "服务器错误"),
    SOCKET_TIME_OUT_ERROR(5003, "socket端口连接超时"),
    CLASS_CAST_ERROR(5004, "类型转换错误"),

    AUTH_ERROR(400, "权限错误"),
    USER_NOT_FOUND(4001, "未找到相关用户信息"),
    ROLE_NOT_FOUND(4002, "未找到对应角色信息"),
    ROLE_CANNOT_DELETED(4003, "存在关联用户，角色信息删除失败"),
    PERMISSION_NOT_FOUND(4004, "为找到对应权限信息"),
    PERMISSION_CANNOT_DELETED(4005, "存在关联角色，权限信息删除失败"),
    UNAUTHORIZED(4006, "暂未登录或token已经过期"),
    FORBIDDEN_REQUEST(4007, "没有相关权限"),


    GATEWAY_ERROR(600, "网关错误"),
    EMPTY_EMAIL_HEADER(6001, "未找到请求头中的email信息"),
    INVALID_EMAIL(6002, "不合法的email格式");


    private int code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
