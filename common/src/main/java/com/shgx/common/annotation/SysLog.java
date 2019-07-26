package com.shgx.common.annotation;

import java.lang.annotation.*;

/**
 * @author: guangxush
 * @create: 2019/07/25
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}


