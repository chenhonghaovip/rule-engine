package com.jd.cho.rule.engine.common.anno;

import com.jd.cho.rule.engine.common.enums.ConstantEnum;
import com.jd.cho.rule.engine.common.enums.FactorTypeEnum;

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

    /**
     * 返回值类型
     *
     * @return 该方法的返回值类型
     */
    FactorTypeEnum returnType() default FactorTypeEnum.TEXT;

    /**
     * @return 取值模式
     * @see com.jd.cho.rule.engine.common.enums.ConstantEnum
     */
    ConstantEnum valueMode() default ConstantEnum.INPUT;

    /**
     * 参数列表
     *
     * @return 参数列表
     */
    String values();
}
