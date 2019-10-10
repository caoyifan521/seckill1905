package com.qfedu.seckill1905.exception;

import com.qfedu.seckill1905.common.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionResolver {

    @ExceptionHandler(MyException.class)
    public JsonResult myException(MyException ex){
        return new JsonResult(ex.getCode(), ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public JsonResult commonException(Exception ex){
        return new JsonResult(500, ex.getMessage());
    }

}
