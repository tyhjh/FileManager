package com.yorhp.filemanager.aspect;
import com.yorhp.filemanager.enums.ResultEnum;
import com.yorhp.filemanager.exception.MlException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.yorhp.filemanager.controller.*.*(..))")

    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {

        if (false) {
            throw new MlException(ResultEnum.VISIT_TOO_OFTEN);
        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url={}", request.getRequestURL());
        //ip
        logger.info("ip={}", request.getRemoteAddr());
        //method
        logger.info("method={}", request.getMethod());
        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
        logger.info("args={}", joinPoint.getArgs());
    }

    @After("log()")
    public void after() {

    }

    @AfterReturning(pointcut = "log()", returning = "object")
    public void doAfterRunning(Object object) {
        logger.info("response={}", object);
    }

}
