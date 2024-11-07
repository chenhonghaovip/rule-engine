package com.jd.cho.rule.engine.domain.model;

import lombok.Data;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleAction {
    private String fieldCode;

    private Object values;
}
