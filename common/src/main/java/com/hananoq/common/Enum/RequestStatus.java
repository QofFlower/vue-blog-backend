package com.hananoq.common.Enum;

import lombok.Getter;

/**
 * @author :花のQ
 * @since 2020/11/20 14:37
 **/
@Getter
public enum RequestStatus {

    REQUEST_SUCCESS(200, null),
    REQUEST_FAIL(4000, "请求错误"),
    UNKNOWN_FAIL(4001, "未知错误"),
    VALIDATE_FAIL(4002, "实体校验异常"),
    AUTHENTICATION_FAIL(4003, "认证异常");


    private int code;
    private String message;

    RequestStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    RequestStatus(int code) {
        this.code = code;
    }

    RequestStatus(String message) {
        this.message = message;
    }
}
