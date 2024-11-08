package com.jd.cho.rule.engine.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CommonDict {

    /**
     * code
     */
    private String code;

    /**
     * 描述
     */
    private Object desc;
}
