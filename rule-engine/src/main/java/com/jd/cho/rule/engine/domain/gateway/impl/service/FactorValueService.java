package com.jd.cho.rule.engine.domain.gateway.impl.service;

import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface FactorValueService {

    /**
     * 通过字段脚本获取当前字段对应的值
     *
     * @param fieldName 字段code
     * @param param     请求参数
     * @return 字段值
     */
    Object getFieldValue(Object fieldName, Map<String, Object> param, Map<String, String> fieldMapping);
}
