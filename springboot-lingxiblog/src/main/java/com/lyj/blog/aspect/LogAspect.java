package com.lyj.blog.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: springboot-lingxiblog
 * @Date: 2020/3/17 21:28
 * @Author: 李玉杰
 * @Description:
 *
 */
@Aspect//开启切面
@Component//开启组件扫描，要不然扫描不到
public class LogAspect {
    private Logger logger=LoggerFactory.getLogger(LogAspect.class);
    /**
     * 定义切面拦截
     * 第一个*是代表拦截的类或者方法的返回为任意的，public和private都可以
     * 第二个*代表任意类
     * 第三个*代表任意方法
     * （..）任意参数
     */
    @Pointcut("execution(* com.lyj.blog.controller.*.*(..))")
    public void log(){

    }


    @Before("log()")//定义在哪个切面之前执行下面的方法
    public void doBefore(JoinPoint joinPoint){
        logger.info("-------doBefore--------");
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();//获取请求Request
        String url=request.getRequestURL().toString();
        String ip=request.getRemoteAddr();//获取ip地址
        //通过JoinPoint获取切面来获取方法和参数  getDeclaringTypeName():类名，getName()：方法名
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        RequestLog requestLog=new RequestLog(url,ip,classMethod,args);
        logger.info("requestLog : {}",requestLog);
        /**
         * 执行结果输出为：
         * requestLog : LogAspect.RequestLog(url=http://localhost:8080/3/lijie,
         * ip=0:0:0:0:0:0:0:1, classMethod=com.lyj.blog.controller.LoginController.index, args=[3, lijie])
         */


        System.out.println("-------doBefore--------");
    }
    @After("log()")//定义在哪个切面之后执行下面的方法
    public void doAfter(){
        logger.info("------------doAfter------------");
        System.out.println("------------doAfter------------");
    }

    /**
     * Pointcut定义在哪个切面执行
     * returning定义拦截的方法执行完方法名的返回值，要入参保持一致
     * @param result
     */
    @AfterReturning(returning = "result" ,pointcut = "log()")
    public void doReturnAfter(Object result){
        logger.info("result : {}",result);
        System.out.println("-------------doReturnAfter-----------");
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class RequestLog{  //请求的日志
        private String url;  //请求的URL地址
        private String ip;   //访问者的ip
        private String classMethod;   //调用的类方法ClassMethod
        private Object[] args;      //类方法的参数
    }
}
