package com.hananoq.common.exception;

import com.hananoq.common.Enum.RequestStatus;
import com.hananoq.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author :花のQ
 * @since 2020/11/22 9:50
 * 全局异常处理类
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Result handler(RuntimeException e) {
        log.error("运行时异常");
        e.printStackTrace();
        return Result.error(RequestStatus.REQUEST_FAIL.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result validator(MethodArgumentNotValidException e) {
        log.error("实体校验异常");
        return Result.error(RequestStatus.VALIDATE_FAIL.getCode()
                , RequestStatus.VALIDATE_FAIL.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result illegal(IllegalArgumentException e) {
        log.error("认证错误");
        return Result.error(RequestStatus.AUTHENTICATION_FAIL.getCode(), e.getMessage());
    }

}
