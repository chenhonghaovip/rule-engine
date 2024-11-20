package com.jd.cho.rule.engine.common.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenhonghao12
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiMethod {

    /**
     * 方法注册code
     *
     * @return string
     */
    String code();

    /**
     * 方法名称
     *
     * @return 方法名称
     */
    String name();
}
