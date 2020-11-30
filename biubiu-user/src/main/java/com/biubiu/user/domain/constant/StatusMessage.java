package com.biubiu.user.domain.constant;

/**
 * 系统错误码
 *
 * @author wbbaijq
 */
public enum StatusMessage {

    PARAM_ERR(10001, "参数为空或错误"),
    NO_DATA(50001, "无数据"),

    ; //固定格式用的，勿动

    /** 状态码 **/
    public int code;
    /** 描述 **/
    public String message;

    StatusMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
