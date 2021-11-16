package com.javaroad.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.javaroad.transaction.common.Results;

/**
 * 自定义异常处理类
 */
@RestControllerAdvice
public class DefaultExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public Results<?> exception(Exception e) {
        return Results.error(500, "服务器繁忙，请稍后再试");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public Results<?> runtimeException(Exception e) {
        return Results.error(500, "服务器繁忙，请稍后再试");
    }


}
