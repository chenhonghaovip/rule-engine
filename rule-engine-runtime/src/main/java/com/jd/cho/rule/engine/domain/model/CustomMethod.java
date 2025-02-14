package com.jd.cho.rule.engine.domain.model;

import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.enums.ConstantEnum;
import com.jd.cho.rule.engine.core.factor.model.RuleFactorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomMethod {
    /**
     * 方法编码
     */
    private String methodCode;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数个数
     */
    private Integer paramCount;

    /**
     * 方法返回值类型（日期、数值、集合、布尔、文本）
     */
    private RuleFactorType returnType;

    /**
     * 常量类型（Input:输入，Enum:枚举，Script:脚本）
     */
    private ConstantEnum constantType;

    /**
     * 常量值
     */
    private List<CommonDict> constantValues;

    /**
     * 方法表达式(QLExpress)
     */
    private String methodExpression;

    /**
     * 参数列表
     */
    private List<CustomMethodParam> customMethodParams;

    @Data
    public static class CustomMethodParam {

        /**
         * 参数名称
         */
        private String paramName;

        /**
         * 参数类型
         */
        private Class<?> paramType;

        /**
         * 是否为系统类型
         */
        private Boolean isSysClassType;
    }
}
