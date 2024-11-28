package com.jd.cho.rule.engine.common.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenhonghao12
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiParam {

    /**
     * 参数名称
     *
     * @return 参数名称
     */
    String name();
}
