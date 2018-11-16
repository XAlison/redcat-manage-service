package com.ywsoftware.oa.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 * @author xiewl
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	String value() default "";
}
