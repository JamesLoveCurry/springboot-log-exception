package com.lyj.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @program: springboot-lingxiblog
 * @Date: 2020/3/17 0:01
 * @Author: 李玉杰
 * @Description:
 */
//该注解是代表找不到界面抛异常来这,捕获throw异常
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(){

    }
    public NotFoundException(String message){
        super(message);
    }
    public NotFoundException(String message,Throwable cause){
        super(message,cause);
    }
}
