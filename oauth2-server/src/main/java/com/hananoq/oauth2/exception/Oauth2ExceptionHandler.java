package com.hananoq.oauth2.exception;

import com.hananoq.common.Enum.RequestStatus;
import com.hananoq.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author :花のQ
 * @since 2020/11/22 17:39
 **/
@RestControllerAdvice
@Slf4j
public class Oauth2ExceptionHandler {

    @ExceptionHandler(InvalidGrantException.class)
    public Result grantException() {
        log.error("授权失败");
        return Result.error(RequestStatus.AUTHENTICATION_FAIL.getCode(), "用户名或密码错误");
    }

}
