package com.hananoq.common.response;

import com.hananoq.common.Enum.RequestStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author :花のQ
 * @since 2020/11/20 14:31
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Result implements Serializable {

    private int code; // 状态码
    private String message; // 返回信息
    private Object data; // 返回数据

    public static Result success(Object data) {
        return new Result()
                .setCode(RequestStatus.REQUEST_SUCCESS.getCode())
                .setMessage(RequestStatus.REQUEST_SUCCESS.getMessage())
                .setData(data);
    }

    public static Result success(int code, String message) {
        return new Result()
                .setCode(code)
                .setMessage(message);
    }

    public static Result success(int code, String message, Object data) {
        return new Result()
                .setMessage(message)
                .setCode(code)
                .setData(data);
    }

    public static Result error(int code, String message) {
        return new Result()
                .setCode(code)
                .setMessage(message);
    }


}
