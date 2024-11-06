package com.jd.cho.rule.engine.domain.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleAction {
    /**
     * fieldCode : field1
     * values : [11,22]
     */

    @JSONField(name = "fieldCode")
    private String fieldCode;
    @JSONField(name = "values")
    private Object values;
}
