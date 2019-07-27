package com.shgx.provider.aop;

import com.alibaba.fastjson.JSON;
import com.shgx.provider.annotation.MyLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: guangxush
 * @create: 2019/07/27
 */
@Component
@Aspect
public class MyLogAction {
    private final String POINT_CUT = "execution(* com.shgx.provider..*(..))";

    @Pointcut(POINT_CUT)
    private void pointcut() {
    }

    @After("pointcut()")
    public void doAfterAdvice(JoinPoint pjp) throws Throwable {
        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();

        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();
        String className = pjp.getTarget().getClass().getName();
        Method method = target.getClass().getMethod(methodName, parameterTypes);
        Logger log = LoggerCache.getLoggerByClassName(className);
        if (null != method) {
            // 获取方法（此为自定义注解）
            MyLog op = method.getAnnotation(MyLog.class);
            if (op != null) {
                // 获取注解的modules 设为操作模块
                log.info("Module\t----->\t" + JSON.toJSONString(op.module()));
                // 获取注解的methods 设为执行方法
                log.info("Method\t----->\t" + JSON.toJSONString(op.method()));
            }
        }
    }

    /**
     * 加入注解自动记录方法日志
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "pointcut()")
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
