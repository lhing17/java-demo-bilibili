package com.example.demo.advice;

import com.example.demo.exception.GseinException;
import com.example.demo.model.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GseinException.class)
    public Result<Void> handleException(GseinException e) {
        System.out.println("我处理GseinException");
        return Result.error(e.getMessage());
    }

//    @ExceptionHandler(RuntimeException.class)
//    public Result<Void> handleException(RuntimeException e) {
//        System.out.println("我处理RuntimeException");
//        return Result.error(e.getMessage());
//    }

//    @ExceptionHandler(Exception.class)
//    public Result<Void> handleException(Exception e) {
//        System.out.println("我处理Exception");
//        return Result.error(e.getMessage());
//    }
}
