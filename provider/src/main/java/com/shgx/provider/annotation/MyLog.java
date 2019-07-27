package com.shgx.provider.annotation;

import java.lang.annotation.*;

/**
 * @author: guangxush
 * @create: 2019/07/27
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    String module() default "";
    String method() default "";
}
