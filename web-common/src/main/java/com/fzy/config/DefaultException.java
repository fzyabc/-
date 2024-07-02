package com.fzy.config;

import com.fzy.core.FException;
import com.fzy.vo.ResultJson;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
    @RestControllerAdvice
        作用于所有的controller控制器上
        找到所有的@RequestMapping注解的类的所有方法上

    @RestControllerAdvice一般都会搭配@ExceptionHandler注解
        做controller的全局异常处理使用

 */
@RestControllerAdvice //找所有的controller的方法
public class DefaultException {

    @ExceptionHandler//全局异常处理
    public ResultJson handler(Exception ex){

        //必须将异常信息打印到控制台上

        //给系统管理员看的信息（开发者）
        ex.printStackTrace();
if (ex instanceof FException){
return ResultJson.failed(ex.getMessage());
}
        //给用户返回的错误信息
        return ResultJson.failed("系统异常，请联系管理员");

    }

}
