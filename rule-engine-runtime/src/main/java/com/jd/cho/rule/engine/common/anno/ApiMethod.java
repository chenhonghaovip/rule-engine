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
     * 默认使用方法名称
     *
     * @return string
     */
    String code() default "";

    /**
     * 方法名称
     * 默认使用方法名称
     *
     * @return 方法名称
     */
    String name() default "";

    /**
     * 返回值类型（默认为text）
     *
     * @return 该方法的返回值类型
     * @see com.jd.cho.rule.engine.common.enums.FactorTypeEnum  默认支持的类型
     * @see com.jd.cho.rule.engine.spi.RuleFactorTypeExtendService 自定义类型需要实现该拓展点
     */
    String returnType() default "text";

    /**
     * 该方法用于存储该方法的取值脚本，即可选值，返回类型必须为 List<CommonDict>
     *
     * @return 取值脚本（QL脚本模式）
     * @see com.jd.cho.rule.engine.common.base.CommonDict
     */
    String valueScript() default "";
}
