package com.shgx.server.log;

import com.alibaba.fastjson.JSON;
import com.shgx.common.annotation.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: guangxush
 * @create: 2019/07/25
 */
@Aspect
@Component
public class SysLogAspect {

    @Pointcut("@annotation(com.shgx.common.annotation.SysLog)")
    public void log() {
    }

    @Before("log()")
    public void beforePointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        SysLog annotation = method.getAnnotation(SysLog.class);
        String value = annotation.value();
        System.out.println("准备"+value);
    }

    @After("log()")
    public void afterPointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        SysLog annotation = method.getAnnotation(SysLog.class);
        String value = annotation.value();
        System.out.println("结束"+value);
    }

    /**
     * 加入注解自动记录方法日志
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "log()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取执行方法的类的名称（包名加类名）
        String className = joinPoint.getTarget().getClass().getName();
        // 获取实例和方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 从缓存中获取日志实例
        Logger log = LoggerCache.getLoggerByClassName(className);
        // 记录日志
        log.info(className + "." + method.getName() + "()");
        Object[] args = joinPoint.getArgs();
        log.info("Params\t----->\t" + JSON.toJSONString(args));
        // 执行方法获取返回值
        Object proceed = joinPoint.proceed();
        // 记录日志
        log.info("Returns\t----->\t" + JSON.toJSONString(proceed));
        // 返回
        return proceed;
    }
}
