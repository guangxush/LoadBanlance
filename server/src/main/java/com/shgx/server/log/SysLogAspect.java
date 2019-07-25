package com.shgx.server.log;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;

import java.lang.reflect.Method;

/**
 * @author: guangxush
 * @create: 2019/07/25
 */
public class SysLogAspect {

    @Pointcut("@annotation(com.shgx.common.annotation.SysLog)")
    public void log() {
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
