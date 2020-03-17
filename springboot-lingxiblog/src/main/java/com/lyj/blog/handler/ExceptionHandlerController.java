package com.lyj.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: springboot-lingxiblog
 * @Date: 2020/3/16 22:26
 * @Author: 李玉杰
 * @Description:
 */
@ControllerAdvice
public class ExceptionHandlerController {
    //日志跟踪
    private final Logger logger=LoggerFactory.getLogger(ExceptionHandlerController.class);

    //开启全局异常捕捉
    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(HttpServletRequest request,Exception e) throws Exception {
        logger.error("Request URL : {}, Exception : {}",request.getRequestURL(),e);
        if (AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class)!=null){
            throw e;
        }
        ModelAndView mav=new ModelAndView();
        //返回界面信息
        mav.addObject("url",request.getRequestURL());
        mav.addObject("exception",e);
        mav.setViewName("error/error");
        return mav;
    }
}
