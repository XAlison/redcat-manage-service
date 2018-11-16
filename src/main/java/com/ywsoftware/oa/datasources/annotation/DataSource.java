package com.ywsoftware.oa.datasources.annotation;

import java.lang.annotation.*;

/**
 * 多数据源注解
 * @author xiewl
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String name() default "";
}
