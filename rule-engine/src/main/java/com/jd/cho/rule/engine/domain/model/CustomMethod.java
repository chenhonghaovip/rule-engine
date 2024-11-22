package com.jd.cho.rule.engine.domain.model;

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
     * 返回值类型
     */
    private String returnType;

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
    }
}
